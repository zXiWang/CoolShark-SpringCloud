package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 向文件中写入文本数据
 */
public class WriteStringDemo {
    public static void main(String[] args) throws IOException {
        //向文件fos.txt中写入字符串
        /*
            文件流的两个构造方法默认为覆盖模式:
            FileOutPutStream(String path)
            FileOutputStream(File file)
            上述两个构造器创建时，如果指定的文件已经存在了，则会将该文件清空(变成0字节)，
            之后通过当前流写出的数据会陆续写入文件中保存。

            文件流的两个构造方法为追加模式:
            FileOutPutStream(String path,boolean append)
            FileOutputStream(File file,boolean append)
            如果第二个参数为true，则文件流打开了追加模式。
            当创建文件流时如果指定的文件已经存在，则文件原数据全部保留，通过当前流写出的
            数据会陆续的追加到文件末尾
         */
//        File file = new File("fos.txt");
//        FileOutputStream fos = new FileOutputStream(file);

        FileOutputStream fos = new FileOutputStream("fos.txt",true);
//        String line = "爱你孤身走暗巷";
        String line = "爱你~";
        /*
            String提供的方法:
            byte[] getBytes(Charset charset)
            将当前字符串按照指定的字符集转换为一组字节
         */
        byte[] data = line.getBytes(StandardCharsets.UTF_8);
        fos.write(data);

        line = "如果你突然打了个喷嚏，啊~那一定是我在想你";
        data = line.getBytes(StandardCharsets.UTF_8);
        fos.write(data);


        System.out.println("写出完毕!");
        fos.close();

    }
}
