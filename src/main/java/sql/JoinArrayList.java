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
            first.parallelStream()
                    .flatMap(v1 -> second.stream()
                            .filter(v2 -> v2.getId() == v1.getId())
                            .map(v2 -> new FinalLine(v1, v2)))
                    .forEach(line -> {
                        try {
                            writer.write(line.toString());
                        } catch (IOException ex) {
                            System.out.println("Не удалось провести запись в файл");
                        }
                    });
        } catch (IOException e) {
            System.out.println("Не удалось провести запись в файл");
        }
    }
}
