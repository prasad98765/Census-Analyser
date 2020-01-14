package censusanalyser;

import CSvBuilderPackage.CSVBuilderException;
import CSvBuilderPackage.CSVBuilderFactory;
import CSvBuilderPackage.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdapter {
    Map<String,CensusDAO> censusStateMap = new TreeMap<>();

    public <E> Map<String, CensusDAO> loadCensusData(Class<E> censusCSVClass, String... filePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath[0]))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<E> censusCSVList = icsvBuilder.getCSVFileInList(reader, censusCSVClass);
            if (censusCSVClass.getName().equals("censusanalyser.IndiaCensusCSV")) {
                StreamSupport.stream(censusCSVList.spliterator(), false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(censusCSV -> censusStateMap.put(censusCSV.state, new CensusDAO(censusCSV)));
            }
            if (censusCSVClass.getName().equals("censusanalyser.USCensusCSV")) {
                StreamSupport.stream(censusCSVList.spliterator(), false)
                        .map(USCensusCSV.class::cast)
                        .forEach(censusCSV -> censusStateMap.put(censusCSV.StateId, new CensusDAO(censusCSV)));
            }
            if (filePath.length == 1) return censusStateMap ;
            this.loadIndianStateCode(filePath[1],censusStateMap);
            return censusStateMap;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.INVALID_DATA);
        }
    }

    public <E> int loadIndianStateCode(String filePath , Map<String, CensusDAO> censusStateMap) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<IndiaStateCode> stateDataList = icsvBuilder.getCSVFileInList(reader, IndiaStateCode.class);
            StreamSupport.stream(stateDataList.spliterator(),false)
                    .filter(IndiaStateCode -> censusStateMap.get(IndiaStateCode.state) != null)
                    .forEach(censusData -> censusStateMap.get(censusData.state).StateId = censusData.StateCode);
            return censusStateMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    @Override
    public <E> Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        return null;
    }
}
