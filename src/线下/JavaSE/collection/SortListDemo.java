package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortListDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
           list.add((int) (Math.random()*100));
        }
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }
}
