package sql;

import java.io.OutputStream;

public interface InnerJoin<T> {
    void innerJoin(T first, T second, OutputStream output);
}
