package 作业.day02;
/**
 * 执行程序，分析并解决问题
 * 
 * NumberFormatException出现的情况通常是由包装类将字符串解析为基本类型时,由于字符串内容不能正确描述基本类型导致该异常.
 * 数字    格式      异常
 * 
 * 
 * @author Xiloer
 *
 */
public class Test04 {
	public static void main(String[] args) {
		/*
		 * 原因:字符串数字有空格无法转,应该先trim()除去
		 */
		String num = "123 ";
		int d = Integer.parseInt(num);
		System.out.println(d);

		/*
		 * 原因:int整数类型无法取小数,转不了
		 */
		num = "123.456";
		d = Integer.parseInt(num);
		System.out.println(num);
		
		/*
		 * 原因:没毛病
		 */
		num = "123";
		d = Integer.parseInt(num);
		System.out.println(d);
	}
}
