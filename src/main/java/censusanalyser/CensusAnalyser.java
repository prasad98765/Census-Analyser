package censusanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {
    Map<String, CensusDAO> censusList = new TreeMap<>();

    public Country country;

    public enum Country {
        INDIA, US
    }

    public CensusAnalyser(Country country) {
        this.country = country;
    }

    public CensusAnalyser() {

    }

    public int loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        censusList = censusAdapterFactory.loadCensusData(country, csvFilePath);
        return censusList.size();
    }


    public String sortingIndiaCensusData() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid File", CensusAnalyserException.ExceptionType.NULL_EXCEPTION);
        }
        List censuslist = censusList.values().stream().sorted(Comparator.comparing(censusData -> censusData.state))
                .map(censusDAO -> censusDAO.getCensusDTO(country)).collect(Collectors.toList());
        String json = new Gson().toJson(censuslist);
        return json;
    }

    public String sortingIndianStateCode() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid File", CensusAnalyserException.ExceptionType.NULL_EXCEPTION);
        }
        List censuslist = censusList.values().stream().sorted(Comparator.comparing(censusData -> censusData.stateId))
                .map(censusDAO -> censusDAO.getCensusDTO(country)).collect(Collectors.toList());
        String json = new Gson().toJson(censuslist);
        return json;
    }

    public String sortingIndiaCensusByPopulation() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid data", CensusAnalyserException.ExceptionType.INVALID_DATA);
        }
        List censuslist = censusList.values().stream().sorted(Comparator.comparing(censusData -> censusData.population))
                .map(censusDAO -> censusDAO.getCensusDTO(country)).collect(Collectors.toList());
        Collections.reverse(censuslist);
        String json = new Gson().toJson(censuslist);
        return json;
    }

    public String sortingIndiaCensusByDensity() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid data", CensusAnalyserException.ExceptionType.INVALID_DATA);
        }
        List censuslist = censusList.values().stream().sorted(Comparator.comparing(censusData -> censusData.densityPerSqKm))
                .map(censusDAO -> censusDAO.getCensusDTO(country)).collect(Collectors.toList());
        Collections.reverse(censuslist);
        String json = new Gson().toJson(censuslist);
        return json;
    }

    public String sortingIndiaCensusByAreaWise() throws CensusAnalyserException {
        if ((censusList == null) || (censusList.size() == 0)) {
            throw new CensusAnalyserException("Invalid data", CensusAnalyserException.ExceptionType.INVALID_DATA);
        }
        List censuslist = censusList.values().stream().sorted(Comparator.comparing(censusData -> censusData.areaInSqKm))
                .map(censusDAO -> censusDAO.getCensusDTO(country)).collect(Collectors.toList());
        Collections.reverse(censuslist);
        String json = new Gson().toJson(censuslist);
        return json;
    }


}




