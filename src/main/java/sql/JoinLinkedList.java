package sql;

import entity.FinalLine;
import entity.Line;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class JoinLinkedList implements InnerJoin<LinkedList<Line>> {

    @Override
    public void innerJoin(LinkedList<Line> first, LinkedList<Line> second, OutputStream outputStream) {
        join(first, second, outputStream);
    }

    public void join(LinkedList<Line> firstList, LinkedList<Line> secondList, OutputStream outputStream) {
        ListIterator<Line> iteratorSecond = secondList.listIterator();
        ListIterator<Line> iteratorFirst = firstList.listIterator();
        int stepBack = 1;
        Line secondListLine;
        Line firstListLine;
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            while (iteratorFirst.hasNext()) {
                firstListLine = iteratorFirst.next();
                while (iteratorSecond.hasNext()) {
                    secondListLine = iteratorSecond.next();
                    if (firstListLine.getId() < secondListLine.getId()) {
                        iteratorBack(iteratorSecond, stepBack);
                        stepBack = 1;
                        break;
                    } else if (firstListLine.getId() == secondListLine.getId()) {
                        writer.write((new FinalLine(firstListLine, secondListLine)).toString());
                        stepBack++;
                        if (!iteratorSecond.hasNext()) {
                            iteratorBack(iteratorSecond, stepBack);
                            stepBack = 1;
                            break;
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Не удалось провести запись в файл");
        }

    }

    private void iteratorBack(ListIterator iterator, int IteratorStepCount) {
        for (int i = 0; i < IteratorStepCount; i++) {
            iterator.previous();
        }
    }

}
