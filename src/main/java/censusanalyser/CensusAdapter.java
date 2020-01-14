package censusanalyser;

import java.util.Map;

public abstract class CensusAdapter {
    public abstract <E> Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException;
}
