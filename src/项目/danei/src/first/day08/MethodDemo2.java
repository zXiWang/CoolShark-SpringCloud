package 项目.danei.src.first.day08;

import java.util.Scanner;

/**
 * 有参无返回值的方法测试类
 */
public class MethodDemo2 {
    public static void main(String[] args) {
        //调用方法时,该方法需要传递实际参数(实参)
        //可以直接传入该方法需要类型的直接量,或者变量
        //nextLine 获取控制台输入的字符串的数据,要用String来接收
        String name = new Scanner(System.in).nextLine();
        sayHi(name,28);
    }

    /**
     * 做一个打招呼的方法(功能) ---> 功能是所有人(大家)都能用到的
     * 语法 : 返回值类型  方法名() {}
     * 形式参数(形参)格式: 数据类型 参数(变量)名
     * 程序的设计 : 对修改关闭 ---> 尽量可能的让功能更加灵活完善,减少代码的修改次数
     */
    static void sayHi(String name,int age) {
        /**
         * 在当前的功能定义时,名字不能够确定
         * 使用者(调用者)来决定,到底给哪个同学打招呼
         */

        System.out.println(name+"同学你好呀~我的年龄是"+age+"岁!");
    }
}
