package censusanalyser;

public class CensusDAO{
    public double areaInSqKm;
    public String state;
    public int Population;
    public double densityPerSqKm;
    public String StateId;
    public int Housingunits;
    public double Totalarea;
    public double Waterarea;
    public double Landarea;
    public double HousingDensity;


    public CensusDAO(IndiaCensusCSV indiaCensusCSV){
        state = indiaCensusCSV.state;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        Population = indiaCensusCSV.population;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
    }

    public CensusDAO(USCensusCSV usCensusCSV){
        StateId = usCensusCSV.StateId;
        state = usCensusCSV.State;
        Population = usCensusCSV.Population;
        areaInSqKm = usCensusCSV.Totalarea;
        Housingunits = usCensusCSV.Housingunits;
        Totalarea = usCensusCSV.Totalarea;
        Waterarea = usCensusCSV.Waterarea;
        Landarea = usCensusCSV.Landarea;
        densityPerSqKm= usCensusCSV.PopulationDensity;
        HousingDensity = usCensusCSV.HousingDensity;

    }

}
