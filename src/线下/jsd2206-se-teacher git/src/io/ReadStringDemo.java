package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 从文件中读取字符串
 */
public class ReadStringDemo {
    public static void main(String[] args) throws IOException {
        //将fos.txt文件中所有字符读取回来
        File file = new File("fos.txt");
        /*
            FileInputStream(String path)
            FileInputStream(File file)
         */
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        fis.read(data);
        /*
            String提供的构造器
            String(byte[] data,Charset charset)
            将给定的字节数组data中所有的字节按照给定的字符集charset转换为一个字符串
         */
        String line = new String(data, StandardCharsets.UTF_8);
        System.out.println(line);

        fis.close();
    }
}
