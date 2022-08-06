package collection;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionDemo2 {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        c.add(new Point(1,2));
        c.add(new Point(2,2));
        c.add(new Point(4,2));
        c.add(new Point(6,2));
        c.add(new Point(7,2));
        c.add(new Point(8,2));
        c.add(new Point(188,2));
        c.add(new Point(199,2));
        System.out.println(c);
        Point p= new Point(1,2);
        System.out.println(c.contains(p));

        c.remove(p);
        System.out.println(c);
    }
}
