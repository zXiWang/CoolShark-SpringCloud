package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * 受元素的equals方法影响的集合操作
 */
public class CollectionDemo2 {
    public static void main(String[] args) {
        Collection c = new ArrayList();
//        Collection c = new HashSet();
        c.add(new Point(1,2));
        c.add(new Point(3,4));
        c.add(new Point(5,6));
        c.add(new Point(7,8));
        c.add(new Point(9,0));
        c.add(new Point(1,2));
        /*
            集合重写了toString方法,格式如下:
            [元素1.toString(), 元素2.toString(), 元素3.toString(), ...]
         */
        System.out.println(c);

        Point p = new Point(1,2);
        /*
            boolean contains(Object o)
            判断当前集合是否包含给定元素,判断依据是给定元素是否与集合元素存在equals比较为true的情况
         */
        boolean contains = c.contains(p);
        System.out.println("是否包含:"+contains);
        /*
            boolean remove(Object o)
            从集合中删除与给定元素equals比较为true的元素.若存在重复元素则只删除一次.
         */
        c.remove(p);
        System.out.println(c);

    }
}
