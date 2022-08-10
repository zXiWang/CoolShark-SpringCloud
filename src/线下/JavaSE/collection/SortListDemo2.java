package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortListDemo2 {
    public static void main(String[] args) {
        List<Point> p = new ArrayList<>();

        p.add(new Point(3, 1));
        p.add(new Point(3, 2));
        p.add(new Point(9, 3));
        p.add(new Point(5, 4));
        p.add(new Point(1, 5));
        System.out.println(p);
        Comparator<Point> c = (p1, p2) -> {
            int s1= (int)(Math.pow(p1.getX(),2)+Math.pow(p1.getY(),2));
            int s2= (int)( Math.pow(p2.getX(),2)+Math.pow(p2.getY(),2));
            return s1-s2;
        };
        Collections.sort(p, c);
        System.out.println(p);

    }
}
