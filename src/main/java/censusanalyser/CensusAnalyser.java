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
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<IndiaCensusCSV> censusCSVList = icsvBuilder.getCSVFileInList(reader,IndiaCensusCSV.class);
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
            List<CSVStates> censusCSVList = icsvBuilder.getCSVFileInList(reader, CSVStates.class);
            return censusCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public String sortingIndiaCensusData(String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<IndiaCensusCSV> arraylist = icsvBuilder.getCSVFileInList(reader, IndiaCensusCSV.class);
            Comparator<IndiaCensusCSV> codeCSVComparator = (o1, o2) -> ((o1.toString().compareTo(o2.toString())) < 0) ? -1 : 1;
            Collections.sort(arraylist, codeCSVComparator);
            String json = new Gson().toJson(arraylist);
            System.out.println(json);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String sortingIndianStateCode(String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<CSVStates> arraylist = icsvBuilder.getCSVFileInList(reader, CSVStates.class);
            Comparator<CSVStates> codeCSVComparator = (o1, o2) -> ((o1.StateCode.compareTo(o2.StateCode)) < 0) ? -1 : ((o1.StateCode.compareTo(o2.StateCode)) > 0) ? 1 : 0;
            Collections.sort(arraylist, codeCSVComparator);
            String json = new Gson().toJson(arraylist);
            System.out.println(json);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}




