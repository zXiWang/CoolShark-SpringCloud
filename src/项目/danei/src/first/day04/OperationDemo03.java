package 项目.danei.src.first.day04;

import java.util.Scanner;

/**
 * 关系运算符的使用测试类
 */
public class OperationDemo03 {
    public static void main(String[] args) {
        /*int a = 50;
        int b = 40;
        boolean result = a > b; //常规的写法
        System.out.println(result); //true

        System.out.println(a < b); //false
        System.out.println(a != b); //true
        System.out.println(a == b); //false
        System.out.println(10 >= 10); //true*/

        //逻辑运算符的使用:
        int a = 5;
        int b = 10;
        int c = 5;

        //&&逻辑与,在与的关系中,见false则false
        //               false && true ---> 结果为false
        boolean result = a > b && b > c;
        System.out.println(result);

        //||逻辑或,在或的关系中,见true则true
        //                 false || true ---> 结果为true
        System.out.println(a > b || b > c);

        //非
        boolean result1 = !(a > b && b > c);
        System.out.println(result1);

        int d = 5;
        int e = 6;
        //当遇到逻辑运算符时,逻辑运算符会将判断表达式分为几个小部分
        //执行顺序是从左到右
        System.out.println(d == e && ++d > 5); //false

        System.out.println(d++ == 5 || ++d == 6);
        System.out.println(d); //6

        /*-----------案例:判断年份是否是闰年---------
        * 判断闰年的条件:
        *   条件1: 年份能够被4整除,并且年份不能够被100整除
        *   或者
        *   条件2: 年份能够被400整除
        *
        *   扫描器功能 : 主要负责扫描并获取控制台(显示台)中用户输入内容.
        */
        Scanner s = new Scanner(System.in); //创建扫描器对象
        System.out.println("请输入一个要判断的年份~,输入完成后回车即可!");
        int year = s.nextInt(); //获取用户在控制台中输入的int类型的数据
        boolean r = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
        System.out.println(r);
    }
}
