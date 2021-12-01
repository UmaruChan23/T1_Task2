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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            first
                    .forEach(it -> second.parallelStream()
                            .filter(sec -> sec.getId() == it.getId())
                            .map(e -> new FinalLine(it, e).toString())
                            .forEach(line -> {
                                try {
                                    writer.write(line);
                                } catch (IOException ex) {
                                    System.out.println("Не удалось провести запись в файл");
                                }
                            }));
        } catch (IOException e) {
            System.out.println("Не удалось провести запись в файл");
        }
    }
}
