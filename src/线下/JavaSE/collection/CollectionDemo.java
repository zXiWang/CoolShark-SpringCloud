package collection;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionDemo {
    public static void main(String[] args) {
        Collection c = new ArrayList();

        c.add("ff");
        c.add("ff");
        c.add("ff");
        c.add("ff");
        c.add("ff");
        c.add("ff");
        c.add("ff");

        System.out.println(c.size());

        System.out.println(c.isEmpty());
        c.clear();
        System.out.println(c.isEmpty());

    }
}
