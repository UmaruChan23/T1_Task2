import entity.Line;
import loader.LoadTable;
import sql.InnerJoin;
import sql.JoinArrayList;
import sql.JoinHashMap;
import sql.JoinLinkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        LoadTable loader = new LoadTable();
        String pathA = "C:\\Users\\fmatorin\\IdeaProjects\\T1_Task2\\src\\main\\resources\\A.txt";
        String pathB = "C:\\Users\\fmatorin\\IdeaProjects\\T1_Task2\\src\\main\\resources\\B.txt";

        ArrayList<Line> first = loader.loadDataFromFile(pathA);
        ArrayList<Line> second = loader.loadDataFromFile(pathB);

        innerJoin(new JoinArrayList(), first, second);

        LinkedList<Line> linkedFirst = new LinkedList<>(first);
        LinkedList<Line> linkedSecond = new LinkedList<>(second);

        innerJoin(new JoinLinkedList(), linkedFirst, linkedSecond);

        HashMap<Long, ArrayList<Line>> firstMap = fillMap(first);
        HashMap<Long, ArrayList<Line>> secondMap = fillMap(second);

        innerJoin(new JoinHashMap(), firstMap, secondMap);
    }

    public static <T> void innerJoin (InnerJoin<T> list, T first, T second) {
        long begin = System.nanoTime();
        list.innerJoin(first, second);
        long end = System.nanoTime();
        System.out.println(list.getClass() + " Время в нанесекундах: " + (end-begin));
    }

    private static HashMap<Long, ArrayList<Line>> fillMap(ArrayList<Line> list) {
        HashMap<Long, ArrayList<Line>> result = new HashMap<>();
        list.forEach(it -> result.putIfAbsent(it.getId(), new ArrayList<>()));
        list.forEach(it -> result.get(it.getId()).add(it));
        return result;
    }
}
