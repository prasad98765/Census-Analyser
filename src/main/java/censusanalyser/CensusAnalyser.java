package censusanalyser;

import CSvBuilderPackage.CSVBuilderException;
import CSvBuilderPackage.CSVBuilderFactory;
import CSvBuilderPackage.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CensusAnalyser {
    List<IndiaCensusDAO> censusList = null;
    List<IndiaStateCodeDAO> StateCodeList = null;
    List<USCensusDAO> usCensusCSVS = null;

    public CensusAnalyser() {
        this.censusList = new ArrayList<IndiaCensusDAO>();
        this.StateCodeList = new ArrayList<IndiaStateCodeDAO>();
        this.usCensusCSVS = new ArrayList<USCensusDAO>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
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
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.INVALID_FILE_DATA_FROMANT);
        }
}

    public int loadIndianStateCode(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<IndiaStateCode> stateDataList = icsvBuilder.getCSVFileInList(reader, IndiaStateCode.class);
            stateDataList.stream().filter(stateData ->
                    StateCodeList.add(new IndiaStateCodeDAO(stateData))).collect(Collectors.toList());
            return StateCodeList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<USCensusCSV> censusCSVList = icsvBuilder.getCSVFileInList(reader, USCensusCSV.class);
            for (int i = 0; i < censusCSVList.size(); i++) {
                this.usCensusCSVS.add(new USCensusDAO(censusCSVList.get(i)));
            }
            return usCensusCSVS.size();
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
            if ((StateCodeList == null) || (StateCodeList.size() == 0)) {
                throw new CensusAnalyserException("Invalid File", CensusAnalyserException.ExceptionType.NULL_EXCEPTION);
            }
        Comparator<IndiaStateCodeDAO> codeCSVComparator = (o1, o2) -> ((o1.StateCode.compareTo(o2.StateCode)) < 0) ? -1 : ((o1.StateCode.compareTo(o2.StateCode)) > 0) ? 1 : 0;
        Collections.sort(StateCodeList, codeCSVComparator);
            String json = new Gson().toJson(StateCodeList);
            System.out.println(json);
            return json;
        }

    public String sortingIndiaCensusByPopulation() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid data", CensusAnalyserException.ExceptionType.INVALID_DATA);
        }
        Comparator<IndiaCensusDAO> codeCSVComparator = (o1, o2) -> ((o1.Population - (o2.Population)) > 0) ? -1 :1;
        Collections.sort(censusList, codeCSVComparator);
        String json = new Gson().toJson(censusList);
        System.out.println(json);
        return json;
    }

    public String sortingIndiaCensusByDensity() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid data", CensusAnalyserException.ExceptionType.INVALID_DATA);
        }
        Comparator<IndiaCensusDAO> codeCSVComparator = (o1, o2) -> ((o1.densityPerSqKm - (o2.densityPerSqKm)) > 0) ? -1 :1;
        Collections.sort(censusList, codeCSVComparator);
        String json = new Gson().toJson(censusList);
        System.out.println(json);
        return json;
    }

    public String sortingIndiaCensusByAreaWise() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid data", CensusAnalyserException.ExceptionType.INVALID_DATA);
        }
        Comparator<IndiaCensusDAO> codeCSVComparator = (o1, o2) -> ((o1.areaInSqKm - (o2.areaInSqKm)) > 0) ? -1 :1;
        Collections.sort(censusList, codeCSVComparator);
        String json = new Gson().toJson(censusList);
        System.out.println(json);
        return json;
    }


}




