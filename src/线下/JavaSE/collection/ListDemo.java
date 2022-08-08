package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
//        for (int i = 0; i < list.size()/2; i++) {
////            String temp = list.get(i);
////            list.set(i, list.get(list.size() - i-1));
////            list.set(list.size() - i-1, temp);
//            list.set(i, list.set(list.size() - i-1, list.get(i)));
//        }
//        for (int i = 0; i<list.size()/2; list.set(i,list.set(list.size()-1-i, list.get(i++))));
        Collections.reverse(list);
        list.add(4,"five");
        System.out.println(list.remove(2));
        System.out.println(list);
    }
}
