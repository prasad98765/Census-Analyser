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
    Map<String, CensusDAO> censusStateMap = new TreeMap<>();

    public <E> int loadIndianStateCode(String filePath, Map<String, CensusDAO> censusStateMap) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<IndiaStateCode> stateDataList = icsvBuilder.getCSVFileInList(reader, IndiaStateCode.class);
            StreamSupport.stream(stateDataList.spliterator(), false)
                    .filter(IndiaStateCode -> censusStateMap.get(IndiaStateCode.state) != null)
                    .forEach(censusData -> censusStateMap.get(censusData.state).stateId = censusData.stateCode);
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
        Map<String, CensusDAO> censusStateMap = super.loadCensusData(IndiaCensusCSV.class, csvFilePath[0]);
        this.loadIndianStateCode(csvFilePath[1], censusStateMap);
        return censusStateMap;
    }
}
