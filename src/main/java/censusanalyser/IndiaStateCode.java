package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCode {

    @CsvBindByName(column = "SrNo", required = true)
    public int srNo;

    @CsvBindByName(column = "StateName", required = true)
    public String state;

    @CsvBindByName(column = "TIN", required = true)
    public int TIN;

    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;

    @Override
    public String toString() {
        return "{" +
                "SrNo:'" + srNo + '\'' +
                ", StateName:'" + state + '\'' +
                ", TIN:'" + TIN + '\'' +
                ", StateCodes:'" + stateCode + '\'' +
                '}';
    }
}
