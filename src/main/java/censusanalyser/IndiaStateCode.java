package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCode {

    @CsvBindByName(column = "SrNo", required = true)
    public int SrNo;

    @CsvBindByName(column = "StateName", required = true)
    public String state;

    @CsvBindByName(column = "TIN", required = true)
    public int TIN;

    @CsvBindByName(column = "StateCode", required = true)
    public String StateCode;

    @Override
    public String toString() {
        return "{" +
                "SrNo:'" + SrNo + '\'' +
                ", StateName:'" + state + '\'' +
                ", TIN:'" + TIN + '\'' +
                ", StateCodes:'" + StateCode + '\'' +
                '}';
    }
}
