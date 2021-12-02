package sql;

import entity.FinalLine;
import entity.Line;

import java.io.*;
import java.util.ArrayList;

public class JoinArrayList extends AbstractJoinArrayList {
    @Override
    protected void join(ArrayList<Line> first, ArrayList<Line> second, OutputStream outputStream) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
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
