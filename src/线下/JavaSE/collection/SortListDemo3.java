package collection;

import java.util.ArrayList;
import java.util.List;

public class SortListDemo3 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("干会活");
        list.add("哎哎哎");
        list.add("咕咕咕咕");
        list.add("天高地厚");
        list.add("英语");
        list.add("谷");
        System.out.println(list);
//        Comparator<String> c = Comparator.comparingInt(String::length);
//        list.sort(c);
        list.sort((o1, o2) -> o1.length() - o2.length());
        System.out.println(list);
    }
}
