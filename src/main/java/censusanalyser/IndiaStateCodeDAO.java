package censusanalyser;

public class IndiaStateCodeDAO {
    public int SrNo;
    public String State;
    public int TIN;
    public String StateCode;

    public IndiaStateCodeDAO(IndiaStateCode csvStates){
        SrNo = csvStates.SrNo;
        State = csvStates.state;
        TIN = csvStates.TIN;
        StateCode = csvStates.StateCode;
    }
}
