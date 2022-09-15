package 作业.day05;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 改正下面程序的错误
 *
 * 向文件testpw.txt中以UTF-8编码写入一行字符串：
 * 你好!我喜欢java!
 *
 * 单词记一记:
 * print 打印
 *
 * @author Xiloer
 *
 */
public class Test01 {
    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("testFile/testpw.txt");
            File file;
            if(!(file=new File("testFile/testpw.txt")).exists()) {
                file.createNewFile();
            }
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("你好!我喜欢java!");
            System.out.println("写出完毕!");
            pw.close();
        }catch(Exception e) {
            System.out.println("出错了!");
        }
    }

}
