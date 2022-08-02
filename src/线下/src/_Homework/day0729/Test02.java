package day0729;

import java.io.*;
import java.util.Scanner;

/**
 * 设计一个类:User
 * 里面有四个属性:String name,String pwd,String nick,int age
 * 定义get,set方法以及toString方法和构造方法
 * <p>
 * 单词记一记:
 * pwd   是单词password的缩写，是密码的意思
 * user  用户
 * <p>
 * <p>
 * 当前程序启动后要求用户顺序输入以上四个信息
 * 然后实例化一个User对象保存着四个信息并将
 * 该对象序列化到文件中。
 * 文件名的命名规则:用户名.obj
 * 比如该用户输入用户名为张三，那么这个对象
 * 序列化后的文件名为:张三.obj
 *
 * @author Xiloer
 */
class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 8759868440078868377L;
    private String name;
    private String pwd;
    private String nick;
    private int age;

    public User(String name, String pwd, String nick, int age) {
        this.name = name;
        this.pwd = pwd;
        this.nick = nick;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nick='" + nick + '\'' +
                ", age=" + age +
                '}';
    }
}

public class Test02 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        User user = null;
        System.out.println("请依次输入name,pwd,nick,age");
        user = new User(scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), scanner.nextInt());
        FileOutputStream fos = new FileOutputStream(user.getName() + ".obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        FileInputStream fis = new FileInputStream(user.getName() + ".obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        oos.writeObject(user);
        System.out.println(ois.readObject().toString());

        oos.close();
        ois.close();
        System.out.println("写入成功!");

    }
}
