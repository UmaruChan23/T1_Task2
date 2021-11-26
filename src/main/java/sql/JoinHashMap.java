package sql;

import entity.FinalLine;
import entity.Line;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class JoinHashMap implements InnerJoin<HashMap<Long, ArrayList<Line>>> {

    @Override
    public void innerJoin(HashMap<Long, ArrayList<Line>> first,
                          HashMap<Long, ArrayList<Line>> second) {
        join(first, second, "src/main/resources/hashMap.txt");
    }

    @Override
    public void innerJoin(HashMap<Long, ArrayList<Line>> first,
                          HashMap<Long, ArrayList<Line>> second,
                          String path) {
        join(first, second, path);
    }

    private void join(HashMap<Long, ArrayList<Line>> first,
                      HashMap<Long, ArrayList<Line>> second,
                      String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
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
