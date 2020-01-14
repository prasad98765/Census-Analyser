package censusanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {
    Map<String,CensusDAO> censusList = new TreeMap<>();

    public CensusAnalyser() {}
    public enum Country{
        INDIA,US
    }

    public <E> int loadCensusData (Country country, String... csvFilePath) throws CensusAnalyserException {
        if (country.equals(CensusAnalyser.Country.INDIA)) {
            return this.loadIndiaCensusData(IndiaCensusCSV.class, csvFilePath);
        } else if (country.equals(CensusAnalyser.Country.US)) {
            return this.loadUSCensusData(USCensusCSV.class, csvFilePath[0]);
        } else {
            throw new CensusAnalyserException("INCORRECT_COUNTRY", CensusAnalyserException.ExceptionType.INCORRECT_COUNTRY);
        }
    }


    public int loadIndiaCensusData(Class  csvClass, String... csvFilePath) throws CensusAnalyserException {
        censusList = new IndiaCensusAdapter().loadCensusData(csvClass , csvFilePath);
        return censusList.size();
    }
    public int loadUSCensusData(Class  csvClass,String csvFilePath) throws CensusAnalyserException {
        censusList = new IndiaCensusAdapter().loadCensusData(csvClass , csvFilePath);
        return censusList.size();
    }

    public String sortingIndiaCensusData() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid File", CensusAnalyserException.ExceptionType.NULL_EXCEPTION);
        }
        List<CensusDAO> censuslist = censusList.values().stream().collect(Collectors.toList());
        Comparator<CensusDAO> codeCSVComparator = (o1, o2) -> ((o1.state.compareTo(o2.state)) < 0) ? -1 : ((o1.state.compareTo(o2.state)) > 0) ? 1 : 0;
        Collections.sort(censuslist, codeCSVComparator);
        String json = new Gson().toJson(censuslist);
        System.out.println(json);
        return json;
    }

    public String sortingIndianStateCode() throws CensusAnalyserException {
            if ((censusList == null) || (censusList.size() == 0)) {
                throw new CensusAnalyserException("Invalid File", CensusAnalyserException.ExceptionType.NULL_EXCEPTION);
            }
        List<CensusDAO> dataList = censusList.values().stream().collect(Collectors.toList());
        Comparator<CensusDAO> codeCSVComparator = (o1, o2) -> ((o1.StateId.compareTo(o2.StateId)) < 0) ? -1 : ((o1.state.compareTo(o2.state)) > 0) ? 1 : 0;
            Collections.sort(dataList, codeCSVComparator);
            String json = new Gson().toJson(dataList);
            System.out.println(json);
            return json;
        }

    public String sortingIndiaCensusByPopulation() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid data", CensusAnalyserException.ExceptionType.INVALID_DATA);
        }
        List<CensusDAO> censuslist = censusList.values().stream().collect(Collectors.toList());
        Comparator<CensusDAO> codeCSVComparator = (o1, o2) -> ((o1.Population - (o2.Population)) > 0) ? -1 :1;
        Collections.sort(censuslist, codeCSVComparator);
        String json = new Gson().toJson(censuslist);
        System.out.println(json);
        return json;
    }

    public String sortingIndiaCensusByDensity() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid data", CensusAnalyserException.ExceptionType.INVALID_DATA);
        }
        List<CensusDAO> censuslist = censusList.values().stream().collect(Collectors.toList());
        Comparator<CensusDAO> codeCSVComparator = (o1, o2) -> ((o1.densityPerSqKm - (o2.densityPerSqKm)) > 0) ? -1 :1;
        Collections.sort(censuslist, codeCSVComparator);
        String json = new Gson().toJson(censuslist);
        System.out.println(json);
        return json;
    }

    public String sortingIndiaCensusByAreaWise() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid data", CensusAnalyserException.ExceptionType.INVALID_DATA);
        }
        List<CensusDAO> censuslist = censusList.values().stream().collect(Collectors.toList());
        Comparator<CensusDAO> codeCSVComparator = (o1, o2) -> ((o1.areaInSqKm - (o2.areaInSqKm)) > 0) ? -1 :1;
        Collections.sort(censuslist, codeCSVComparator);
        String json = new Gson().toJson(censuslist);
        System.out.println(json);
        return json;
    }


}




