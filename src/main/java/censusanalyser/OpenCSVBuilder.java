package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.lang.reflect.Array;
import java.util.Iterator;

public class OpenCSVBuilder<E> implements ICSVBuilder{

    public Iterator<E> getCSVFileIterable(Reader reader, Class CSVClass) throws CSVBuilderException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(CSVClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();

        } catch (IllegalStateException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }   catch (RuntimeException r) {
            throw new CSVBuilderException(r.getMessage(),
                    CSVBuilderException.ExceptionType.INCORRECT_FILE_DATA);
        }
    }
}
