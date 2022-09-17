package 作业.day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 在一个List集合中生成20个随机整数(100以内)
 * 然后按照从小到大的顺序排列，排好后再将第6个-第15个元素
 * 按照从大到小顺序排列，并最终输出集合
 * 例如:
 * 1,2,3,4,5,15,14,13,12,11,10,9,8,7,6,16,17,18,19,20
 * @author pc
 *
 */
public class Test01 {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i <20; i++) {
			list.add((int)(Math.random()*100));
		}
		list.sort(null);
		Collections.reverse(list.subList(5,15));
		System.out.println(list);
	}
}








