package 项目.danei.src.first.day05;

import java.util.Scanner;

/**
 * 双路分支的使用测试类
 */
public class IFElseDemo {
    public static void main(String[] args) {
        /**
         * 双路分支的语法结构:
         *      if(条件) { //如果
         *          //满足条件所执行的代码块1
         *      } else { //否则
         *          //不满足条件所执行的代码块2
         *      }
         *
         *      执行过程:
         *          选取判断if小括号中的条件
         *          如果条件成立,则执行满足条件所执行的代码块1
         *          如果条件不成立,则执行不满足条件所执行的代码块2
         *  作业练习:购物满500 打8折,不满500 打9折
         */
        System.out.println("请输入金额~");
        double price = new Scanner(System.in).nextDouble();
        if (price >= 500) {
            price *= 0.8;
            System.out.println("打完8折后,应付"+price+"元!");
        }else {
            price *= 0.9;
            System.out.println("打完9折后,应付"+price+"元!");
        }
        System.out.println("实付"+price+"元");
    }
}














