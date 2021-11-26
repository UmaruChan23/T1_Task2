package sql;

public interface InnerJoin<T> {
    void innerJoin(T first,T second, String path);
}
