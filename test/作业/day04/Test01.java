package 作业.day04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 改正下面程序的错误
 * <p>
 * 程序实现的功能需求:复制一个文件
 *
 * @author Xiloer
 */
public class Test01 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis
                = new FileInputStream("testFile/test.txt");
        FileOutputStream fos
                = new FileOutputStream("testFile/test_cp.txt");

        int d;
        while ((d = fis.read()) != -1) {
            fos.write(d);
        }
        System.out.println("复制完毕!");
        fis.close();
        fos.close();
    }
}




