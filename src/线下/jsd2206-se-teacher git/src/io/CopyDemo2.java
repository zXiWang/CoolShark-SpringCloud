package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 提高每次读写的数据量减少实际读写的次数可以提高读写效率
 *
 * 块读写:一次读写一组字节
 */
public class CopyDemo2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("01.rmvb");
        FileOutputStream fos = new FileOutputStream("01_cp.rmvb");
        /*
            java.io.InputStream超类上定义了块读字节的操作:
            int read(byte[] data)
            一次性读取给定字节数组data总长度的字节量并将读取到的所有字节存入到该数组中。
            返回值表达本次实际读取的字节数量。如果返回值为整数-1则表示读取到了流的末尾。

            文件内容（6字节）:
            11001100 00110011 11110000 00001111 10101010 01010101

            byte[] data = new byte[4];//4字节长的数组
            int len = 0;//记录每次实际读取的字节数

            第一次调用len = fis.read(data);
            一次性尝试从文件中读取data数组长度的字节量(本案例:4个字节)
            11001100 00110011 11110000 00001111 10101010 01010101
            ^^^^^^^^ ^^^^^^^^ ^^^^^^^^ ^^^^^^^^
            data:{11001100 00110011 11110000 00001111} 数组中存放的是本次读取的4字节内容
            len:4 这里的4是表达本次实际读取到了4个字节

            第二次调用len = fis.read(data);
            一次性尝试从文件中读取data数组长度的字节量(本案例:4个字节)
            11001100 00110011 11110000 00001111 10101010 01010101 文件末尾
                                                ^^^^^^^^ ^^^^^^^^ ^^^^^^^^ ^^^^^^^^
                                                本次实际仅读取到了2个字节
            data:{10101010 01010101 11110000 00001111}
                  |--本次读取的字节--||---上次的旧数据---|
            len:2 本次实际仅读取到了2个字节

            第三次调用len = fis.read(data);
            11001100 00110011 11110000 00001111 10101010 01010101 文件末尾
                                                                  ^^^^^^^^ ^^^^^^^^ ^^^^^^^^ ^^^^^^^^
            data:{10101010 01010101 11110000 00001111}
                  |--------没有变化，都是旧数据--------|
            len:-1 表达文件末尾


            java.io.OutputStream超类上定义了块写操作
            void write(byte[] data)
            一次性将给定的字节数组中所有的字节写出

            byte[] data:{10101010 01010101 11110000 00001111}
            fos.write(data)
            文件数据:
            10101010 01010101 11110000 00001111


            void write(byte[] data,int offset,int len)
            一次性将给定的字节数组从下标offset处开始的连续len个字节写出
            byte[] data:{10101010 01010101 11110000 00001111}
                                  ^^^^^^^^ ^^^^^^^^
            fos.write(data,1,2):将data数组中从下标1开始的连续2个字节一次性写出

            文件数据:
            01010101 11110000

         */
        //编译后:byte[] data = new byte[10240];
        byte[] data = new byte[1024*10];//10kb
        int len = 0;//记录每次实际读取的字节数

        long start = System.currentTimeMillis();
        while( (len = fis.read(data)) != -1  ){
            fos.write(data,0,len);
        }
        long end = System.currentTimeMillis();
        System.out.println("复制完毕,耗时"+(end-start)+"ms");

        fis.close();
        fos.close();


    }
}
