package 作业.day11;

import java.io.*;

/**
 * 浏览器发送过来的请求内容每个字符单独读取出来并输出到控制台
 * @author Xiloer
 *
 */
public class Test02 {
	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream(new File("testFile/test.txt"));

		int d;
		StringBuilder builder = new StringBuilder();
		char pre = 'a', cur = 'a';
		while ((d = in.read()) != -1) {
			cur = (char) d;
			if (pre == 13 && cur == 10) {
				break;
			}
			builder.append(cur);
			pre = cur;
		}
		System.out.println(builder.toString().trim());
	}
}
