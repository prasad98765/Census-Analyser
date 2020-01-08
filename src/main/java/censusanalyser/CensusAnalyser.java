package censusanalyser;




import CSvBuilderPackage.CSVBuilderException;
import CSvBuilderPackage.CSVBuilderFactory;
import CSvBuilderPackage.ICSVBuilder;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;

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


    public JSONArray sortingIndiaCensusData(String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = icsvBuilder.getCSVFileIterable(reader, IndiaCensusCSV.class);
            List arraylist = new ArrayList();
            while (censusCSVIterator.hasNext()) {
                arraylist.add(censusCSVIterator.next());
            }
            Comparator<IndiaCensusCSV> codeCSVComparator = (o1,o2)-> ((o1.toString().compareTo(o2.toString()))<0)?-1:1;
            Collections.sort(arraylist, codeCSVComparator);
            JSONArray jsonArray = new JSONArray();
            for (int i=0; i<arraylist.size(); i++){
                jsonArray.put(arraylist.get(i));
            }
            System.out.println(jsonArray);
            return jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
        return null;
    }

        public JSONArray sortingIndianStateCode(String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStates> censusCSVIterator = icsvBuilder.getCSVFileIterable(reader, CSVStates.class);
            List arraylist = new ArrayList();
            while (censusCSVIterator.hasNext()) {
                arraylist.add(censusCSVIterator.next());
            }
            Comparator<CSVStates> codeCSVComparator = (o1,o2)-> ((o1.StateName.compareTo(o2.StateName))<0)?-1:1;
            Collections.sort(arraylist,codeCSVComparator);
            JSONArray jsonArray = new JSONArray();
            for (int i=0; i<arraylist.size(); i++){
                jsonArray.put(arraylist.get(i));
            }
            System.out.println(jsonArray);
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}




