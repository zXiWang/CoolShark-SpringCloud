package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 使用文件流完成文件的复制操作
 */
public class CopyDemo {
    public static void main(String[] args) throws IOException {
//        FileInputStream fis = new FileInputStream("image.jpg");
//        FileOutputStream fos = new FileOutputStream("image_cp.jpg");
        FileInputStream fis = new FileInputStream("01.rmvb");
        FileOutputStream fos = new FileOutputStream("01_cp.rmvb");
        /*
            image.jpg文件内容
            11001100 00110011 11110000 00001111 10101010 01010101 ...

            第1次调用:d = fis.read();
            11001100 00110011 11110000 00001111 10101010 01010101 ...
            ^^^^^^^^
            读取的字节
            d的2进制:00000000 00000000 00000000 11001100

            fos.write(d);
            d:00000000 00000000 00000000 11001100
                                         ^^^^^^^^
                                         写出的字节
            image_cp.jpg文件数据:
            11001100
         */
        int d;//记录每次读取的字节内容
        long start = System.currentTimeMillis();//获取当前系统时间的毫秒值
        while(  (d = fis.read()) != -1    ) {//若读取到了-1则应当停止循环，没有读取到-1则应当抄
            fos.write(d);
        }
        long end = System.currentTimeMillis();//获取当前系统时间的毫秒值

        System.out.println("复制完毕，耗时:"+(end-start)+"ms");
        fos.close();
        fis.close();
    }
}
