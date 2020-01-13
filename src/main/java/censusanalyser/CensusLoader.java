package censusanalyser;

import CSvBuilderPackage.CSVBuilderException;
import CSvBuilderPackage.CSVBuilderFactory;
import CSvBuilderPackage.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CensusLoader {
    List<CensusDAO> censusList = new ArrayList<>();
    public <E> List<CensusDAO> loadCensusData(String filePath,  Class<E> censusCSVClass) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<E> censusCSVList=icsvBuilder.getCSVFileInList(reader,censusCSVClass);
            if (censusCSVClass.getName().equals("censusanalyser.IndiaCensusCSV") )
                censusCSVList.stream().filter(censusData -> censusList.add(new CensusDAO((IndiaCensusCSV) censusData))).collect(Collectors.toList());
            if (censusCSVClass.getName().equals("censusanalyser.USCensusCSV"))
                censusCSVList.stream().filter(censusData -> censusList.add(new CensusDAO((USCensusCSV) censusData))).collect(Collectors.toList());
            return censusList;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.INVALID_FILE_DATA_FROMANT);
        }
    }
}
