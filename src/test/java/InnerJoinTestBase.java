import entity.Line;
import org.junit.jupiter.api.Assertions;
import sql.InnerJoin;

import java.io.*;
import java.util.List;

public class InnerJoinTestBase {
    protected static final int SIZE = 50000;

    private final List<Line> first;
    private final List<Line> second;
    private final ByteArrayOutputStream outputStream;

    public InnerJoinTestBase(List<Line> first, List<Line> second) {
        this.first = first;
        this.second = second;
        outputStream = new ByteArrayOutputStream();

        Assertions.assertEquals(0, first.size());
        Assertions.assertEquals(0, second.size());
        for (int i = 0; i < SIZE; ++i) {
            first.add(new Line(i + 1, String.format("First Name %d", i + 1)));
            second.add(new Line(i + 1, String.format("Last Name %d", i + 1)));
        }

        Assertions.assertEquals(SIZE, first.size());
        Assertions.assertEquals(SIZE, second.size());
    }

    public void testJoin(InnerJoin joiner) {
        joiner.innerJoin(first, second, outputStream);

        try (var reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(outputStream.toByteArray())))) {
            int i = 0;
            var arr = reader.lines().toArray();
            Assertions.assertEquals(SIZE, arr.length);
            for (var line : arr) {
                Assertions.assertEquals(String.format("%d,First Name %d,Last Name %d", i + 1, i + 1, i + 1), line);
                ++i;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
