package sql;

import entity.FinalLine;
import entity.Line;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JoinArrayList implements InnerJoin<ArrayList<Line>> {

    @Override
    public void innerJoin(ArrayList<Line> first, ArrayList<Line> second, String path) {
        join(first, second, path);
    }

    private void join(ArrayList<Line> first, ArrayList<Line> second, String path) {
        first.forEach(it ->
                second.forEach(element -> {
                    if (it.getId() == element.getId()) {
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
                            writer.append((new FinalLine(it, element)).toString());
                        } catch (IOException e) {
                            System.out.println("Не удалось провести запись в файл");
                        }
                    }
                }));
    }
}
