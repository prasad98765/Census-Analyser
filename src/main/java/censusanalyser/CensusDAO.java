package censusanalyser;

public class CensusDAO {
    public double areaInSqKm;
    public String state;
    public int population;
    public double densityPerSqKm;
    public String stateId;
    public int housingunits;
    public double totalarea;
    public double waterarea;
    public double landarea;
    public double housingDensity;


    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        population = indiaCensusCSV.population;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
    }

    public CensusDAO(USCensusCSV usCensusCSV) {
        stateId = usCensusCSV.StateId;
        state = usCensusCSV.State;
        population = usCensusCSV.Population;
        areaInSqKm = usCensusCSV.Totalarea;
        housingunits = usCensusCSV.Housingunits;
        totalarea = usCensusCSV.Totalarea;
        waterarea = usCensusCSV.Waterarea;
        landarea = usCensusCSV.Landarea;
        densityPerSqKm = usCensusCSV.PopulationDensity;
        housingDensity = usCensusCSV.HousingDensity;

    }


    public Object getCensusDTO(CensusAnalyser.Country country) {
        if (country.equals(CensusAnalyser.Country.US))
            return new USCensusCSV(stateId, state, population, housingDensity, areaInSqKm, totalarea, waterarea, landarea, densityPerSqKm, housingunits);
        return new IndiaCensusCSV(state, population, areaInSqKm, densityPerSqKm);
    }

}

