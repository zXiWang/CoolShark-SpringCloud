package 作业.day04;

import java.io.*;

/**
 * 改正下面程序的错误
 * <p>
 * 程序实现需求:使用缓冲流完成文件的复制操作
 *
 * @author Xiloer
 */
public class Test02 {
    public static void main(String[] args) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("testFile/test.txt"));

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("testFile/test_cp.txt"));

        int d = 0;
        if ((d = bis.read()) == -1) {
            bos.write(d);
        }
        System.out.println("复制完毕!");
    }
}
