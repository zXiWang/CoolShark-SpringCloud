package 项目.danei.src.first.day04;

import java.util.Scanner;

/**
 * 赋值运算符的使用测试类
 */
public class OperationDemo04 {
    public static void main(String[] args) {
        /*int a = 10;
        a += 40; //等价于 a = a + 40;
        System.out.println(a);

        a -= 25; //等价于 a = a -25;
        System.out.println(a);

        a *= 1; //等价于 a = a * 1;
        System.out.println(a);

        a /= 25; //等价于 a = a / 25;
        System.out.println(a);

        a %= 3; //等价于 a = a % 3;
        System.out.println(a);


         * 面试题:
         *      规定:如果两个不同的类型进行运算时,结果会转为两个类型中更大的类型

        byte b = 5;
        //b = b + 10; //编译错误,byte和int运算时,结果为int类型
        b += 10; //使用拓展赋值时,系统会自动做强转, b = (byte)(b + 10);*/

        //2.三元运算符
        int a =50;
        int b =100;
        //       boolean? 条件成立时返回的值1 : 条件不成立返回的值2
        //      值1 和 值2 的类型必须要一致,因为我们接收三元表达式返回的结果
        int max = a > b ? a : b;
        double r = a > b ? 1.0 : 2.0;
        System.out.println(max); //100
        System.out.println(r);

        int c = 2;
        String result = c % 2 == 0 ? "是偶数" : "是奇数";
        System.out.println(result);

        //3.字符串拼接符
        System.out.println(10 + 10 + "" + 30); //2030
        System.out.println("" + 10 + 10 + 30); //101030
        System.out.println(10 + 10 + 30 + ""); //50

        int age = 10;
        //我的年龄是10岁!
        System.out.println("我的年龄是"+age+"岁!");
    }
}
