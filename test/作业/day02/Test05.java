package 作业.day02;

import java.util.Scanner;

/**
 * 程序需求:
 * 程序启动后，实例化一个Person，然后要求用户输入用户名
 * 和年龄.并加以验证:
 * 名字至少一个字符，否则输出:姓名不能为空!
 * 年龄必须是0-100之间的数字，否则输出:年龄不合法!
 * <p>
 * 都输入正确时最后输出该用户信息
 * <p>
 * 将下面写好的代码解除注释改正里面的错误使得程序可以正常
 * 运行。
 * 异常记一记:
 * 下面会出现的每一种异常先翻译异常的名字，然后解决该异常后
 * 思考是什么原因引起的错误并在注释中标注出来
 *
 * @author Xiloer
 */
public class Test05 {


    public static void main(String[] args) {
        Person p = new Person();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入姓名:");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            System.out.println("姓名不能为空!");
            return;
        } else {
            p.setName(name);

        }
        System.out.println("请输入年龄:");
        //输入不匹配异常
        String age = scanner.nextLine();
        if (!age.matches("[1-9]\\d?")) {
            System.out.println("年龄不合法!");
            return;
        } else {
            p.setAge(Integer.parseInt(age));
        }

        System.out.println("姓名:" + p.getName());
        System.out.println("年龄:" + p.getAge());
    }


}

class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}