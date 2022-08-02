import java.io.*;
import java.util.Scanner;

/**
 * 改错
 * <p>
 * 程序实现的是简易记事本工具。程序启动后向pw.txt文件写内容
 * 用户输入的每一行字符串都写入到文件中，单独输入exit时
 * 程序退出。
 *
 * @author Xiloer
 */
public class Test04 {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(("课外/作业/8.1/pw.txt")))), true);
        Scanner scanner = new Scanner(System.in);
        String line;
        while (!("exit".equals(line = scanner.nextLine()))) {
            pw.println(line);
        }
        pw.close();
//		FileInputStream fos = new FileInputStream("pw.txt");
//		OutputStreamWriter osw = new OutputStreamWriter(fos,"UFT-8");
//		BufferedWriter bw = new BufferedWriter(osw);
//		PrintWriter pw = new PrintWriter(bw,true);
//		
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("请开始输入内容");
//		while(true) {
//			String str = scanner.nextLine();
//			if("exit".equals(str)) {
//				break;
//			}
//			pw.println(srt);
//			pw.close();
//		}
    }
}
