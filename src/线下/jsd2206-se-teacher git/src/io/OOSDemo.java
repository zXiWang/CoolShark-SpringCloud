package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 对象流
 * java.io.ObjectInputStream和ObjectOutputStream
 *
 * 对象流的功能:进行对象序列化与反序列化
 * 对象序列化由对象输出流完成:将一个java对象按照其结构转换为一组字节的过程
 * 对象反序列化由对象输入流完成:将一组字节还原为一个java对象的过程
 *
 */
public class OOSDemo {
    public static void main(String[] args) throws IOException {
        //将一个Person对象写入文件person.obj中
        String name = "刘桑";
        int age = 55;
        String gender = "男";
        String[] otherInfo = {"技术好","拍片儿一流","大家的启蒙老师"};
        Person p = new Person(name,age,gender,otherInfo);

        //文件输出流(低级流):将字节写入指定文件中
        FileOutputStream fos = new FileOutputStream("person.obj");
        //对象输出流(高级流):将一个java对象进行序列化
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        /*
            序列化时抛出异常:
            java.io.NotSerializableException
            说明序列化的类没有实现可序列化接口:java.io.Serializable

            这里将对象写出涉及到两个操作:
            对象经对象输出流时，会被文件输出流转换为一组字节,这个过程称为对象序列化
            序列化后的字节再经过文件流被写入文件中(硬盘中)，这个过程称为数据持久化
            所谓持久化就是可以长久保存了
         */
        oos.writeObject(p);
        System.out.println("写出完毕！");
        oos.close();

    }
}
