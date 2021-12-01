package loader;

import entity.Line;

import java.io.*;
import java.util.ArrayList;

public class LoadTable {
    public ArrayList<Line> loadDataFromFile(String path) {
        ArrayList<Line> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(createLine(line));
            }
        } catch (IOException ex) {
            System.out.println("Не удалось прочитать данные из файла " + path);
        }
        return lines;
    }

    private Line createLine(String line) {
        String[] splitLine = line.split(",");
        int id = 0;
        try {
            id = Integer.parseInt(splitLine[0]);
            if (id < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            System.out.println("Некорректный id " + splitLine[0]);
        }
        return new Line(id, splitLine[1]);
    }
}
