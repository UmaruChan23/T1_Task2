package sql;

import entity.FinalLine;
import entity.Line;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class JoinLinkedList implements InnerJoin<LinkedList<Line>>{

    @Override
    public void innerJoin(LinkedList<Line> first, LinkedList<Line> second, String path) {
        join(first, second, path);
    }

    private void join(LinkedList<Line> first, LinkedList<Line> second, String path) {
        ListIterator<Line> listIteratorFirst = first.listIterator();
        CycleIterator<Line> cycleIterator = new CycleIterator<>(second);
        Iterator<Line> listIteratorSecond = cycleIterator.cycle().iterator();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            while (listIteratorFirst.hasNext()) {
                Line firstLine = listIteratorFirst.next();
                while (listIteratorSecond.hasNext()) {
                    Line secondLine = listIteratorSecond.next();
                    if (firstLine.getId() == secondLine.getId()) {
                        writer.write((new FinalLine(firstLine, secondLine)).toString());
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Не удалось провести запись в файл");
        }
    }

    static class CycleIterator<T> extends LinkedList<T> {
        public CycleIterator(Collection<T> collection) { super(collection);}
        public Iterable<T> cycle () {
            return new Iterable<T>() {
                @Override
                public Iterator<T> iterator() {
                    return new Iterator<T>() {
                        int index = 0;
                        @Override
                        public boolean hasNext() {
                            boolean result = index < size();
                            if (result) {
                                return result;
                            } else {
                                index = 0;
                                return result;
                            }
                        }

                        @Override
                        public T next() {
                            return get(index++);
                        }
                    };
                }
            };
        }
    }
}
