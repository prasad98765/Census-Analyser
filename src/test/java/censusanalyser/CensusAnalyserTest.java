package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

//import static censusanalyser.CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "./src/main/resources/IndiaStateCensusData.txt";
    private static final String WRONG_CSV_FILE_DATA = "/home/admin1/Downloads/CensusAnalyser/src/test/resources/WrongData.csv";
    private static final String INDIA_STATE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String Null_DATA_CSV_PATH="/home/admin1/Downloads/CensusAnalyser/src/test/resources/NullData.csv";
    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongType_ShouldCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

        @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_DATA);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_DATA_FROMANT, e.type);
        }

    }

    @Test
    public void givenIndiaCensusData_WithWrongHeader_ShouldCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numberOfStateCode = censusAnalyser.loadIndianStateCode(INDIA_STATE_CSV_FILE_PATH);
            Assert.assertEquals(37,numberOfStateCode);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaStateData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndianStateCode(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaStatesData_WithWrongType_ShouldCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateData_WithWrongDelimiter_ShouldCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_DATA);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaStateData_WithWrongHeader_ShouldCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_DATA);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_DATA_FROMANT, e.type);
        } catch (RuntimeException r){
        }
    }
    @Test
    public void givenIndianCensusCsv_WithSorting_ShouldReturn1stElement() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String list = censusAnalyser.sortingIndiaCensusData();
        IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(list,IndiaCensusCSV[].class);
        Assert.assertEquals(true,indiaCensusCSVS[0].state.contains("Andhra Pradesh"));
    }
    @Test
    public void givenIndianCensusCsv_WithSorting_ShouldReturnLastElement() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String list = censusAnalyser.sortingIndiaCensusData();
        IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(list,IndiaCensusCSV[].class);
        Assert.assertEquals(true,indiaCensusCSVS[28].state.contains("West Bengal"));
    }
    @Test
    public void givenIndianStatesCsv_WithSorting_ShouldReturn1stElement() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndianStateCode(INDIA_STATE_CSV_FILE_PATH);
        String list = censusAnalyser.sortingIndianStateCode();
        IndiaStateCode[] indiaStateData = new Gson().fromJson(list, IndiaStateCode[].class);
        Assert.assertEquals("Andhra Pradesh New",indiaStateData[0].StateName);
    }
    @Test
    public void givenIndianStatesCsv_WithSorting_ShouldReturnLastElement() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndianStateCode(INDIA_STATE_CSV_FILE_PATH);
        String list = censusAnalyser.sortingIndianStateCode();
        IndiaStateCode[] indiaStateData = new Gson().fromJson(list, IndiaStateCode[].class);
        Assert.assertEquals(true,indiaStateData[36].StateName.contains("West Bengal"));
    }

    @Test
    public void givenIndianStatesCsv_ShouldReturnNullException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndianStateCode(Null_DATA_CSV_PATH);
            String list = censusAnalyser.sortingIndianStateCode();
            IndiaStateCode[] indiaStateData = new Gson().fromJson(list, IndiaStateCode[].class);
            Assert.assertEquals(true,indiaStateData[36].StateName.contains("West Bengal"));
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianStateCodeCsv_ShouldReturnNullException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(Null_DATA_CSV_PATH);
            String list = censusAnalyser.sortingIndianStateCode();
            IndiaStateCode[] indiaStateData = new Gson().fromJson(list, IndiaStateCode[].class);
            Assert.assertEquals(true,indiaStateData[36].StateName.contains("West Bengal"));
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianStateCensus_ReturnSortedInLargestPopulationState() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String list = censusAnalyser.sortingIndiaCensusByPopulation();
        IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(list,IndiaCensusCSV[].class);
        Assert.assertEquals(true,indiaCensusCSVS[0].state.contains("Uttar Pradesh"));
    }

    @Test
    public void givenIndianStateCensus_ReturnSortedInLowestPopulationState() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String list = censusAnalyser.sortingIndiaCensusByPopulation();
            IndiaCensusCSV[] indiaCensusCSVS = new Gson().fromJson(list, IndiaCensusCSV[].class);
            Assert.assertEquals(true, indiaCensusCSVS[28].state.contains("Sikkim"));
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}
