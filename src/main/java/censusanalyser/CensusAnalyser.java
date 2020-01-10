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
    List<IndiaCensusDAO> censusList = null;
    List<CSVStates> censusStateCode = null;

    public CensusAnalyser() {
        this.censusList = new ArrayList<IndiaCensusDAO>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<IndiaCensusCSV> censusCSVList=icsvBuilder.getCSVFileInList(reader,IndiaCensusCSV.class);
            for (int i=0; i < censusCSVList.size(); i++){
                this.censusList.add(new IndiaCensusDAO(censusCSVList.get(i)));
            }
            return censusList.size();
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
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid File", CensusAnalyserException.ExceptionType.NULL_EXCEPTION);
        }
        Comparator<IndiaCensusDAO> codeCSVComparator = (o1, o2) -> ((o1.state.compareTo(o2.state)) < 0) ? -1 : ((o1.state.compareTo(o2.state)) > 0) ? 1 : 0;
        Collections.sort(censusList, codeCSVComparator);
        String json = new Gson().toJson(censusList);
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




