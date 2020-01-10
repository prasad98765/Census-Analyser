package censusanalyser;

public class IndiaStateCodeDAO {
    public int SrNo;
    public String StateName;
    public int TIN;
    public String StateCode;

    public IndiaStateCodeDAO(IndiaStateCode csvStates){
        SrNo = csvStates.SrNo;
        StateName = csvStates.StateName;
        TIN = csvStates.TIN;
        StateCode = csvStates.StateCode;
    }
}
