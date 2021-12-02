package sql;

import entity.FinalLine;
import entity.Line;

import java.io.*;
import java.util.ArrayList;

public class JoinArrayList implements InnerJoin<ArrayList<Line>> {

    @Override
    public void innerJoin(ArrayList<Line> first, ArrayList<Line> second, OutputStream outputStream) {
        join(first, second, outputStream);
    }

    private void join(ArrayList<Line> first, ArrayList<Line> second, OutputStream outputStream) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            first.parallelStream()
                    .flatMap(v1 -> second.parallelStream()
                            .filter(v2 -> v2.getId() == v1.getId())
                            .map(v2 -> new FinalLine(v1, v2))
                            .sequential())
                    .sequential()
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
