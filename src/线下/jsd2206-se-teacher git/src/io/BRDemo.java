package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 缓冲字符输入流
 * 特点:1,块读文本数据加速
 *     2,可以按行读取字符串
 */
public class BRDemo {
    public static void main(String[] args) throws IOException {
        //将当前源程序输出到控制台上
        FileInputStream fis = new FileInputStream(
                "./src/io/BRDemo.java"
        );
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        /*
            BufferedReader提供了一个方法:
            String readLine()
            该方法可以读取一行字符串。并将这行内容返回(返回的内容最后不含有换行符)，
            如果该行为空行(只有换行符)，那么方法会返回一个空字符串
            如果流读取到了末尾，则返回值为null
         */
        String line;
        while((line = br.readLine()) != null){
            System.out.println(line);
        }

        br.close();
    }
}
