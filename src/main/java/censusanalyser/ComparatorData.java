package censusanalyser;

import java.util.Comparator;

public class ComparatorData implements Comparator {

    @Override
    public int compare(Object o, Object t1) {
        if (o.toString().compareTo(t1.toString()) < 0) {
            return -1;
        } else if (o.toString().compareTo(t1.toString()) > 0) {
            return 1;
        } else if (o.toString().compareTo(t1.toString())== 0) {
            return  0;
        }
        return 0;
    }

}
