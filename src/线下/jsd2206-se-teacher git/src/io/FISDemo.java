package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件输入流，用于从文件中读取字节数据
 */
public class FISDemo {
    public static void main(String[] args) throws IOException {
        //将当前项目目录下的fos.dat文件读取回来
        FileInputStream fis = new FileInputStream("fos.dat");
        /*
            java.io.InputStream超类上定义了读取一个字节的方法:
            int read()
            读取1个字节，并将对应的2进制存入返回的int值的"低八位"上，而高24位2进制全部
            补0.
            如果返回值为int型的整数-1，则表达读取到了流的末尾
         */
        /*
            fos.dat中的样子:
            00000001 00000011

            第一次调用int d = fis.read()
            读取的是fos.dat文件第一个字节:
            00000001 00000011
            ^^^^^^^^
            读取的字节

            read方法返回的int值2进制的样子:00000000 00000000 00000000 00000001
            因此int d = fis.read()之后d的整数为:1
         */
        int d = fis.read();
        System.out.println(d);
        /*
            fos.dat中的样子:
            00000001 00000011

            第二次调用d = fis.read()
            读取的是fos.dat文件第二个字节:
            00000001 00000011
                     ^^^^^^^^
                     读取的字节

            read方法返回的int值2进制的样子:00000000 00000000 00000000 00000011
            因此d = fis.read()之后d的整数为:3
         */
        d = fis.read();
        System.out.println(d);

        /*
            fos.dat中的样子:
            00000001 00000011

            第三次调用d = fis.read()
            读取的是fos.dat文件第三个字节:
            00000001 00000011
                              ^^^^^^^^
                              文件末尾!

            read方法返回的int值2进制的样子:11111111 11111111 11111111 11111111
            因此d = fis.read()之后d的整数为:-1
         */
        d = fis.read();
        System.out.println(d);//-1

        fis.close();
    }
}
