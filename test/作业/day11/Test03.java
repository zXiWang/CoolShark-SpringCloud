package 作业.day11;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 进阶
 * 实现读取浏览器发送过来的一行字符串
 * <p>
 * 可以参考day05晚课练习
 * 浏览器发送过来的内容的特点:
 * 1:内容全是英文，数字和符号，都是单字节编码的字符
 * 2:每一行字符串后面跟着两个不可见的字符:回车符和换行符
 * 而这两个字符对应的编码数字是13,10
 * 就好比'a'对应的编码数字是97
 * <p>
 * 因此可以连续读取若干字符，如果上次读取的字符==13
 * 本次读取的字符==10就可以停止，并将之前的字符组成一个
 * 字符串并输出
 *
 * @author Xiloer
 */
public class Test03 {
    public static void main(String[] args) throws IOException {
        String line;
        while (true) {
            line = readLine();
            if (line.isEmpty()) {
                break;
            }
            System.out.println(line);
        }


    }

    private static String readLine() throws IOException {
//        模拟请求地址
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
        return builder.toString().trim();
    }
}

