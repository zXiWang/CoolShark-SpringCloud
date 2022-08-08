package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayToListDemo {
    public static void main(String[] args) {
        String[] array = new String[] { "a", "b", "c" };
        System.out.println("array:"+ Arrays.toString(array));

        List<String> list = Arrays.asList(array);
        System.out.println("list:"+ list);

        list.set(0,"b");
        System.out.println("list:"+list);
        System.out.println("array:"+ Arrays.toString(array));

        array[2]="d";
        System.out.println("list:"+list);
        System.out.println("array:"+ Arrays.toString(array));

        List<String> list2 = new ArrayList<>(list);
        list2.add("55");
        System.out.println("list2:"+ list2);

    }
}
