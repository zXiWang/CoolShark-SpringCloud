package io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * 缓冲字符流
 * java.io.BufferedWriter和BufferedReader
 * 缓冲字符流是一对高级流，内部默认维护一个8k长度的char数组，读写一定是转换为块读写
 * 来保证读写效率。
 *
 * java.io.PrintWriter是具有自动行刷新的缓冲字符输出流(内部总是连接着BufferedWriter
 * 作为缓冲加速部分)。而PW自身的特点是可以按行写出字符串且具有自动行刷新功能。
 *
 */
public class PWDemo {
    public static void main(String[] args) throws FileNotFoundException {
        //向文件pw.txt中写入文本数据
        /*
            PrintWriter提供了直接对文件做写操作的构造器
            PrintWriter(File file)
            PrintWriter(String path)
         */
        PrintWriter pw = new PrintWriter("pw.txt");
        pw.println("我祈祷拥有一个透明的心灵，和会流泪的眼睛。");
        pw.println("给我再去相信的勇气，越过谎言去拥抱你。");
        System.out.println("写出完毕!");
        pw.close();
    }
}
