package collection;

import java.util.ArrayList;
import java.util.List;

public class ListDemo3 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<10;i++) {
            list.add(i);
        }
        System.out.println(list);
//        List<Integer> list2 = list.subList(2,8);

//        System.out.println(list2);

//        for(int i=0;i<list2.size();list2.set(i,list2.get(i++)*10));
//        System.out.println(list2);
        list.subList(2,9).clear();
        System.out.println(list);
    }
}
