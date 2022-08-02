package 项目.danei.src.first.day06;

import java.util.Random;
import java.util.Scanner;

/**
 * do...while测试类
 */
public class DoWhileDemo {
    public static void main(String[] args) {
        /**
         * do...while 语法结构
         * 三要素 1.循环变量的初始化  2.基于循环变量的条件  3.循环变量的改变
         *
         *  第1要素
         *  do{
         *      //循环的代码块
         *      第3要素
         *  } while(第2要素);
         *
         *  执行过程:
         *      先执行一次do代码块中的代码
         *      再去判断while后面的条件是否成立
         *      如果成立,再执行一次do代码块中的代码
         *      再去判断while后面的条件是否成立
         *      ...
         *      直到判断while后面的条件不成立时,代码则继续向下面执行
         *
         *   小练习:
         *      每天7点到直播间 X5
         */
        /*int count = 1; //第1要素
        do {
            System.out.println("每天7点到直播间");
            count++; //第3要素
        } while(count <= 5); //第2要素
        System.out.println("代码继续向后执行...");*/


        //随机数的使用
        double r = Math.random();//会返回一个[0~1)的随机小数
        //System.out.println(r);
        double r2 = Math.random() * 100; //会返回一个[0~100)的随机小数
        //System.out.println(r2);
        int r3 = (int) (Math.random() * 100);
        //System.out.println(r3);

        //Math.random() * (max - min) + min 设置区间的随机数
        int r4 = (int) (Math.random() * (100 - 50) + 50); //[50,100)
        System.out.println(r4);

        int i = 50 + new Random().nextInt(50); //[0,100)
        System.out.println(i);

        /**
         * 猜大小:
         *  程序运行后,随机生成0~100的随机数
         *  提示用户在控制台输入要猜的数字
         *  如果用户猜对了,则提示恭喜你猜对了,程序向下执行
         *  如果用户猜错了 :
         *      >如果用户输入的数字大于系统随机产生的数字,提示猜大了
         *      >如果用户输入的数字小于系统随机产生的数字,提示猜小了
         */
        int number = new Random().nextInt(100); //随机数
        int guess;
        int count = 0;
        do {
            System.out.println("请输入您猜的数字是多少!");
            guess = new Scanner(System.in).nextInt(); //用户输入的数字
            count++;
            /*if (guess == number) { //用户输入的数据和随机数相等的话
                System.out.println("恭喜您猜对了!");
            } else*/
            if(guess > number) {
                System.out.println("猜大了");
            } else if(guess < number) {
                System.out.println("猜小了");
            }
        }while (guess != number);//循环条件 : 猜不对就一直猜
        System.out.println("恭喜您猜对了!总共猜了"+count+"次!"); //执行到这一行,意味着猜对了
    }
}
