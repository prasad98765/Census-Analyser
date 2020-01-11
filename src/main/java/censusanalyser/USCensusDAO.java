package censusanalyser;

public class USCensusDAO {
    public String StateId;
    public String State;
    public int Population;
    public int Housingunits;
    public double Totalarea;
    public double Waterarea;
    public double Landarea;
    public double PopulationDensity;
    public double HousingDensity;

    public USCensusDAO(USCensusCSV usCensusCSV){
        StateId = usCensusCSV.StateId;
        State = usCensusCSV.State;
        Population = usCensusCSV.Population;
        HousingDensity = usCensusCSV.HousingDensity;
        Totalarea = usCensusCSV.Totalarea;
        Waterarea = usCensusCSV.Waterarea;
        Landarea = usCensusCSV.Landarea;
        PopulationDensity = usCensusCSV.PopulationDensity;
        HousingDensity = usCensusCSV.HousingDensity;

    }
}
