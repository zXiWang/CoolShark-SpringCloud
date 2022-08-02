package io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 自行完成流连接的创建
 */
public class PWDemo2 {
    public static void main(String[] args) throws IOException {
        //向文件中按行写入文本数据
        //1创建文件流(低级流，字节流):向文件中写入字节数据
        FileOutputStream fos = new FileOutputStream("pw2.txt");//如果希望对文件追加写，要在这里打开追加模式
        //2创建转换流(高级流，字符流):衔接字节与字符流，负责将写出的字符转换为字节
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);//若需要明确字符集，则应当在转换流上指定
        //3创建缓冲字符流(高级流，字符流):块写文本数据加速
        BufferedWriter bw = new BufferedWriter(osw);
        //4创建PrintWriter(高级流，字符流):按行写出字符串，自动行刷新
        /*
            当我们主动将PrintWriter连接在一个流上时(构造方法第一个参数为连接在哪个流上)
            那么PrintWriter的构造器就支持再传入一个boolean型参数的重载，如果这个值为
            true时，则打开了自动行刷新功能。
            所谓自动行刷新:
            当我们使用println方法写出一行字符串时，会自动进行一次flush动作。
            注意:调用write，print方法是不会自动flush的。
         */
        PrintWriter pw = new PrintWriter(bw,true);

        /*
            实现简易记事本功能。
            将控制台上输入的每一行字符串都按行写入到文件里
            如果在控制台上单独输入exit,则程序退出。
         */
        Scanner scanner  = new Scanner(System.in);
        while(true){
            String line = scanner.nextLine();//获取用户在控制台输入的一行字符串
            /*
                开发中如果出现字符串变量与字面量(直接量)进行equals比较时，要使用
                字面量.equals(变量)的形式。因为字面量一定非空，可以避免空指针的产生
             */
            if("exit".equals(line)){
                break;//如果用户输入的是退出，则停止循环
            }
            pw.println(line);
            /*
                java中所有的输出流(无论是字符还是字节流)都支持flush方法。
                不过只有缓冲流的flush是真实将自身缓冲区中缓存的数据做强制写出的。
                而其他的高级流中flush方法的实现仅仅是调用了其连接的流的flush，将
                该动作传递下去。
             */
//            pw.flush();
        }

        System.out.println("写出完毕");
        pw.close();
    }
}
