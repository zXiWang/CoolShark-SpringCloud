package collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * java集合框架
 * java.util.Collection接口,java中所有的集合都实现了该接口,集合像数组一样,可以保存一组元素.并且
 * 对于元素的操作全部封装成了方法,使用便捷.
 * 集合提供了多种不同的数据结构.
 *
 * Collection下面两个常见的子类型:
 * java.util.List接口:线性表,是一个可重复且有序的集合
 * java.util.Set接口:不可重复的集合.
 * 这里重复指的是元素是否重复,而判定重复的标准是依靠元素equals方法比较的结果,equals方法返回true则为重复.
 */
public class CollectionDemo1 {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        /*
            boolean add(E e)
            将给定元素添加到集合中,返回值表示是否添加成功
         */
        c.add("one");
        c.add("two");
        c.add("three");
        c.add("four");
        c.add("five");
        System.out.println(c);
        /*
            int size()
            返回当前集合元素个数.
         */
        int size = c.size();
        System.out.println("size:"+size);

        /*
            boolean isEmpty()
            判断当前集合是否为空集(当且仅当size=0时,为空集)
         */
        boolean isEmpty = c.isEmpty();
        System.out.println("是否为空集:"+isEmpty);
        /*
            void clear()
            清空集合
         */
        c.clear();
        System.out.println("集合已清空");
        System.out.println(c);
        System.out.println("size:"+c.size());
        System.out.println("是否为空集:"+c.isEmpty());
    }
}







