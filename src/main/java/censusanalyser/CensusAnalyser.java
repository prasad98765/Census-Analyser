package censusanalyser;

import CSvBuilderPackage.CSVBuilderException;
import CSvBuilderPackage.CSVBuilderFactory;
import CSvBuilderPackage.ICSVBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStates> censusCSVIterator = icsvBuilder.getCSVFileIterable(reader, IndiaCensusCSV.class);
            return getCount(censusCSVIterator);
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
            Iterator<CSVStates> censusCSVIterator = icsvBuilder.getCSVFileIterable(reader, CSVStates.class);
            return getCount(censusCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        int numOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return numOfEnteries;
    }


    public List sortingloadIndiaCensusData(String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = icsvBuilder.getCSVFileIterable(reader, IndiaCensusCSV.class);
            List arraylist = new ArrayList();
            while (censusCSVIterator.hasNext()) {
                arraylist.add(censusCSVIterator.next());
            }
            Collections.sort(arraylist, new ComparatorData());
            for (int i = 0; i < arraylist.size(); i++) {
               System.out.println(arraylist.get(i));
            }
            return arraylist;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
        return null;
    }

        public List sortingloadIndianStateCode(String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStates> censusCSVIterator = icsvBuilder.getCSVFileIterable(reader, CSVStates.class);
            List arraylist = new ArrayList();
            while (censusCSVIterator.hasNext()) {
                arraylist.add(censusCSVIterator.next());
            }
            Collections.sort(arraylist.subList(2,2),new ComparatorData());
            for (int i=0; i<arraylist.size();i++){
                System.out.println(arraylist.get(i));
            }
            return arraylist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}




