package censusanalyser;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "./src/main/resources/IndiaStateCensusData.txt";
    private static final String WRONG_CSV_FILE_DATA = "/home/admin1/Downloads/CensusAnalyser/src/test/resources/WrongData.csv";
    private static final String INDIA_STATE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";

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
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
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
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA, e.type);
        }
    }

    @Test
    public void givenIndiaStateData_WithWrongHeader_ShouldCustomException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_DATA);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_DATA, e.type);
        }
    }

    @Test
    public void givenIndianCensusCsv_WithSorting_ShouldReturnElement() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.sortingIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
    }

    @Test
    public void givenIndianCensusCsv_WithSorting_ShouldReturn1stElement() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        JSONArray list = censusAnalyser.sortingIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(true,list.get(0).toString().contains("Andhra Pradesh"));
    }
    @Test
    public void givenIndianCensusCsv_WithSorting_ShouldReturnlastElement() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        JSONArray list = censusAnalyser.sortingIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(true,list.get(28).toString().contains("West Bengal"));
    }
    @Test
    public void givenIndianStatesCsv_WithSorting_ShouldReturnElement() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.sortingIndianStateCode(INDIA_STATE_CSV_FILE_PATH);
    }
    @Test
    public void givenIndianStatesCsv_WithSorting_ShouldReturn1stElement() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        JSONArray list = censusAnalyser.sortingIndianStateCode(INDIA_STATE_CSV_FILE_PATH);
        Assert.assertEquals(true,list.get(0).toString().contains("Andhra Pradesh New"));
    }
    @Test
    public void givenIndianStatesCsv_WithSorting_ShouldReturnlastElement() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        JSONArray list = censusAnalyser.sortingIndianStateCode(INDIA_STATE_CSV_FILE_PATH);
        Assert.assertEquals(true,list.get(36).toString().contains("West Bengal"));
    }
    @Test
    public void givenIndianStatesCsv_SortingBasedOnSateCode_ShouldReturnElement() throws Exception {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        JSONArray list = censusAnalyser.sortingUsingMap(INDIA_STATE_CSV_FILE_PATH);
        //censusAnalyser.sortingIndianStateCode(INDIA_STATE_CSV_FILE_PATH);
    }



}
