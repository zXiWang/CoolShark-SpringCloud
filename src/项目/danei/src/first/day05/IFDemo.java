package 项目.danei.src.first.day05;

import java.util.Scanner;

/**
 * 单路分支的使用测试类
 */
public class IFDemo {
    public static void main(String[] args) {
        /**
         * 单路分支的语法结构:
         *    if(条件) {
         *      //满足条件所执行的代码逻辑(代码块)
         *    }
         *
         *    执行过程:
         *      先判断if中小括号中的条件是否成立
         *      若成立,则执行(大括号中)满足条件的代码块
         *      若不成立,程序则跳过(大括号中)满足条件的代码块,继续向下执行
         */

        System.out.println("请输入您要付款的金额,回车即可!");
        double price = new Scanner(System.in).nextDouble();
        if (price >= 500) { //如果金额满500,打8折
            price *= 0.8;
            System.out.println("8折后应付:"+price+"元");
        }
        System.out.println("用户已付款:"+price+"元");
    }
}
