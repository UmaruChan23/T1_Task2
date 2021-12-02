package sql;

import entity.FinalLine;
import entity.Line;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class NaiveJoinArrayList extends AbstractJoinArrayList {
    protected void join(ArrayList<Line> first, ArrayList<Line> second, OutputStream outputStream) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            for (var firstItem : first) {
                for (var secondItem : second) {
                    if (firstItem.getId() == secondItem.getId())
                    {
                        var finalLine = new FinalLine(firstItem, secondItem);
                        writer.write(String.valueOf(finalLine));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не удалось провести запись в файл");
        }
    }
}
