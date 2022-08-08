package collection;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionDemo4 {
    public static void main(String[] args) {
        Collection c1= new ArrayList();
        c1.add("java");
        c1.add("c++");
        c1.add(".net");


        Collection c2= new ArrayList();
        c2.add("android");
        c2.add("ios");
        c2.add("java");


        Collection c3= new ArrayList();
        c3.add("c++");
        c3.add("android");
//        c3.add("php");

        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);
        System.out.println("c3: " + c3);
        System.out.println("初始");
        c1.addAll(c2);
        boolean contains=c1.containsAll(c3);
        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);
        System.out.println("c3: " + c3);
        System.out.println("包含所有:"+contains);

//        c1.retainAll(c3);
        c1.removeAll(c3);
        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);
        System.out.println("c3: " + c3);
//        System.out.println("c1: " + c1);
//        System.out.println("c2: " + c2);
    }
}
