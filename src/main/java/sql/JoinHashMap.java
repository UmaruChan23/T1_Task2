package sql;

import entity.FinalLine;
import entity.Line;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JoinHashMap implements InnerJoin<HashMap<Long, ArrayList<Line>>> {

    @Override
    public void innerJoin(HashMap<Long, ArrayList<Line>> first,
                          HashMap<Long, ArrayList<Line>> second,
                          OutputStream outputStream) {
        join(first, second, outputStream);
    }

    private void join(HashMap<Long, ArrayList<Line>> first,
                      HashMap<Long, ArrayList<Line>> second,
                      OutputStream outputStream) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            for (Map.Entry<Long, ArrayList<Line>> entry : first.entrySet()) {
                long id = entry.getKey();
                if (second.containsKey(id)) {
                    ArrayList<Line> firstValues = entry.getValue();
                    ArrayList<Line> secondValues = second.get(id);
                    for (Line firstLine : firstValues) {
                        for (Line secondLine : secondValues) {
                            writer.write((new FinalLine(firstLine, secondLine)).toString());
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Не удалось провести запись в файл");
        }
    }
}
