import entity.Line;
import loader.LoadTable;
import sql.InnerJoin;
import sql.JoinArrayList;
import sql.JoinHashMap;
import sql.JoinLinkedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class InnerJoinTest {
    public static void main(String[] args) {
        LoadTable loader = new LoadTable();
        ArrayList<Line> first;
        ArrayList<Line> second;
        String arrayListPath = "src/main/resources/arrayList.txt";
        String linkedListPath = "src/main/resources/linkedList.txt";
        String hashMapPath = "src/main/resources/hashMapList.txt";

        if (args.length >= 2) {
            first = loader.loadDataFromFile(args[0]);
            second = loader.loadDataFromFile(args[1]);
        } else{
            System.out.println("Введите пути к двум исходным файлам");
            return;
        }

        switch (args.length) {
            case 5 : {
                hashMapPath = args[4];
            }
            case 4 : {
                linkedListPath = args[3];
            }
            case 3 : {
                arrayListPath = args[2];
                break;
            }
            default : {
                if (args.length > 5) {
                    arrayListPath = args[2];
                    linkedListPath = args[3];
                    hashMapPath = args[4];
                }
                break;
            }
        }

        innerJoin(new JoinArrayList(), first, second, arrayListPath);

        LinkedList<Line> linkedFirst = new LinkedList<>(first);
        LinkedList<Line> linkedSecond = new LinkedList<>(second);

        Collections.sort(linkedFirst);
        Collections.sort(linkedSecond);

        innerJoin(new JoinLinkedList(), linkedFirst, linkedSecond, linkedListPath);

        HashMap<Long, ArrayList<Line>> firstMap = fillMap(first);
        HashMap<Long, ArrayList<Line>> secondMap = fillMap(second);

        innerJoin(new JoinHashMap(), firstMap, secondMap, hashMapPath);
    }

    public static <T> void innerJoin (InnerJoin<T> list, T first, T second, String path) {
        long begin = System.nanoTime();
        list.innerJoin(first, second, path);
        long end = System.nanoTime();
        System.out.println(list.getClass() + " Время в нанесекундах: " + (end-begin));
    }

    private static HashMap<Long, ArrayList<Line>> fillMap(ArrayList<Line> list) {
        //больше стримов
        HashMap<Long, ArrayList<Line>> result = new HashMap<>();
        list.forEach(it -> result.putIfAbsent(it.getId(), new ArrayList<>()));
        list.forEach(it -> result.get(it.getId()).add(it));
        return result;
    }
}
