package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CollectionToArrayDemo {
    public static void main(String[] args) {
        Collection<String> str = new ArrayList<>();
        str.add("one");
        str.add("two");
        str.add("three");
        str.add("four");
        str.add("five");
        System.out.println(str);
        String[] str1=str.toArray(new String[0]);
        System.out.println(Arrays.toString(str1));

    }
}
