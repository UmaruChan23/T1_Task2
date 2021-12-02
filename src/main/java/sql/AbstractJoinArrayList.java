package sql;

import entity.Line;

import java.io.OutputStream;
import java.util.ArrayList;

public abstract class AbstractJoinArrayList implements InnerJoin<ArrayList<Line>> {

    @Override
    public void innerJoin(ArrayList<Line> first, ArrayList<Line> second, OutputStream outputStream) {
        join(first, second, outputStream);
    }

    protected abstract void join(ArrayList<Line> first, ArrayList<Line> second, OutputStream outputStream);
}
