package censusanalyser;

import CSvBuilderPackage.CSVBuilderException;
import CSvBuilderPackage.CSVBuilderFactory;
import CSvBuilderPackage.ICSVBuilder;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CensusAnalyser {
    List<IndiaCensusCSV> censusCSVList = null;
    List<CSVStates> censusStateCode = null;
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
             censusCSVList = icsvBuilder.getCSVFileInList(reader,IndiaCensusCSV.class);
            return censusCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public int loadIndianStateCode(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            censusStateCode = icsvBuilder.getCSVFileInList(reader, CSVStates.class);
            return censusStateCode.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public String sortingIndiaCensusData() throws CensusAnalyserException {
        if ((censusCSVList == null) || (censusCSVList.size() == 0)) {
            throw new CensusAnalyserException("Invalid File", CensusAnalyserException.ExceptionType.NULL_EXCEPTION);
        }
        Comparator<IndiaCensusCSV> codeCSVComparator = (o1, o2) -> ((o1.toString().compareTo(o2.toString())) < 0) ? -1 : ((o1.toString().compareTo(o2.toString())) > 0) ? 1 : 0;
        Collections.sort(censusCSVList, codeCSVComparator);
        String json = new Gson().toJson(censusCSVList);
        System.out.println(json);
        return json;
    }

    public String sortingIndianStateCode() throws CensusAnalyserException {
            if ((censusStateCode == null) || (censusStateCode.size() == 0)) {
                throw new CensusAnalyserException("Invalid File", CensusAnalyserException.ExceptionType.NULL_EXCEPTION);
            }
            Comparator<CSVStates> codeCSVComparator = (o1, o2) -> ((o1.StateCode.compareTo(o2.StateCode)) < 0) ? -1 : ((o1.StateCode.compareTo(o2.StateCode)) > 0) ? 1 : 0;
            Collections.sort(censusStateCode, codeCSVComparator);
            String json = new Gson().toJson(censusStateCode);
            System.out.println(json);
            return json;
        }
}




