package 作业.day10;

import java.util.ArrayList;

/**
 * 生成10个0-100之间的不重复的随机数,并输出
 * @author Xiloer
 *
 */
public class Test03 {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(list.size()<10){
			int number= (int) (Math.random()*100);
			if(!list.contains(number)){
				list.add(number);
				System.out.println(number);
			}
		}
	}
}
