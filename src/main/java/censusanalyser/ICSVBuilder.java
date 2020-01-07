package censusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<E> {
   public Iterator<E> getCSVFileIterable(Reader reader, Class<E> CSVClass) throws CSVBuilderException;

}
