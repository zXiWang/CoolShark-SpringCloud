package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IteratorDemo {
    public static void main(String[] args) {
        Collection collection = new ArrayList();
        collection.add("one");
        collection.add("#");
        collection.add("two");
        collection.add("#");
        collection.add("three");
        collection.add("#");
        collection.add("four");
        collection.add("#");
        collection.add("five");
        System.out.println(collection);

        Iterator it= collection.iterator();
        while(it.hasNext()) {
            String str=it.next().toString();
            if("#".equals(str)) {
                it.remove();
            }
            System.out.println(str);
        }
        System.out.println(collection);
    }
}
