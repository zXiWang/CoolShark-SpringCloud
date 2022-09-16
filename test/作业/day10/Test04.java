package 作业.day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 将整数1-100存入一个List集合中并输出
 * 之后将集合中40-60的元素翻转后并输出
 *
 * @author Xiloer
 */
public class Test04 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }
        List<Integer> l = list.subList(list.indexOf(40), list.indexOf(60) + 1);
        Collections.reverse(l);
        System.out.println(list);
    }
}
