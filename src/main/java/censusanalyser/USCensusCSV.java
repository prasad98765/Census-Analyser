package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {
    public static String state;
    @CsvBindByName(column = "State Id", required = true)
    public String StateId;

    @CsvBindByName(column = "State", required = true)
    public String State;

    @CsvBindByName(column = "Population", required = true)
    public int Population;

    @CsvBindByName(column = "Housing units", required = true)
    public int Housingunits;

    @CsvBindByName(column = "Total area", required = true)
    public double Totalarea;

    @CsvBindByName(column = "Water area", required = true)
    public double Waterarea;

    @CsvBindByName(column = "Land area", required = true)
    public double Landarea;

    @CsvBindByName(column = "Population Density", required = true)
    public double PopulationDensity;

    @CsvBindByName(column = "Housing Density", required = true)
    public double HousingDensity;

    public USCensusCSV(String stateId, String state, int population, double housingDensity, double areaInSqKm, double totalarea, double waterarea, double landarea, double densityPerSqKm, int housingunits) {
        StateId = stateId;
        State = state;
        Population = population;
        Housingunits = housingunits;
        Totalarea = totalarea;
        Waterarea = waterarea;
        Landarea = landarea;
        HousingDensity = housingDensity;
    }

    public USCensusCSV() {
    }

    @Override
    public String toString() {
        return "{" +
                "StateId='" + StateId + '\'' +
                ", State='" + State + '\'' +
                ", Population='" + Population + '\'' +
                ", Housing units='" + Housingunits + '\'' +
                ", Total area='" + Totalarea + '\'' +
                ", Water area='" + Waterarea + '\'' +
                ", Land area='" + Landarea + '\'' +
                ", Population Density='" + PopulationDensity + '\'' +
                ", Housing Density='" + HousingDensity + '\'' +
                '}';
    }
}
