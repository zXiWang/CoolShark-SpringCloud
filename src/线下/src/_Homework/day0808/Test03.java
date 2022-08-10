package day0808;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成10个0-100之间的不重复的随机数,并输出
 *
 * @author Xiloer
 */
public class Test03 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int j = (int) (Math.random() * 100);
            if (list.contains(j)) {
                i--;
                continue;
            }
            list.add(j);

        }
        System.out.println(list);
    }
}
