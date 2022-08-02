package day0729;

import java.io.*;

/**
 * 改正下面程序的错误
 * <p>
 * 程序实现需求:使用缓冲流完成文件的复制操作
 *
 * @author Xiloer
 */
public class Test01 {
    public static void main(String[] args) throws IOException {
    	File testFile = new File("test.txt");
    	if(!testFile.exists()) {
            testFile.createNewFile();
		}
        try {
            FileInputStream fis = new FileInputStream("test.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);

            FileOutputStream fos = new FileOutputStream("test_cp.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            int d = 0;
            if ((d = bis.read()) != -1) {
                bos.write(d);
            }
            System.out.println("复制完毕!");
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
