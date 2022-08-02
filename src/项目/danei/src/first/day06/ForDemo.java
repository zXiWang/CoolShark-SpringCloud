package 项目.danei.src.first.day06;

import java.util.Random;
import java.util.Scanner;

/**
 * for循环的使用测试类
 */
public class ForDemo {
    public static void main(String[] args) {
        /**
         * for 循环语法结构 :
         * 三要素 1.循环变量的初始化  2.基于循环变量的条件  3.循环变量的改变
         *
         * for(①int i = 0;②i < 3;③i++){
         *      //循环体④
         * }
         *
         * 执行过程 :
         *      先① --- ② --- ④
         *      再③ --- ② --- ④
         *      再③ --- ② --- ④
         *      ...
         *      直到不满足②,循环则不再继续执行,代码向下执行
         *
         *  好好学习,多敲代码!!! X3
         */

        /*for (int i = 0; i < 3; i++){
            System.out.println("好好学习,多敲代码!!!");
        }
        System.out.println("代码向下执行");*/

        /**
         * 循环10次,输出1-10之间的数,如果是奇数则输出奇数,如果是偶数则输出偶数
         *
         * continue 关键字 : 遇到continue关键字,代表中断本次循环,直接进行下一次循环
         */
        /*for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.println(i+"是偶数");
                continue; //中断本次循环,直接进行下一次循环
            }
            System.out.println(i+"是奇数");
        }*/

        /**
         *  break 关键字 : 遇到break关键字,代表结束本层所有循环
         *  同学跑十圈,但准备跑第六圈时,由于生病不接着跑了
         */
        /*for (int i = 0; i < 10; i++) {
            if (i > 4) {
                System.out.println("突然间生病了!");
                break;
            }
            System.out.println("同学跑了"+(i+1)+"圈");
        }*/

        //1.出题  2.答题  3.判题
        Scanner s = new Scanner(System.in);
        int score = 0;
        for (int i = 0; i < 10; i++) {
            int number1 = new Random().nextInt(100);
            int number2 = new Random().nextInt(100);
            System.out.println("("+(i+1)+")"+number1+" + "+number2+" = ?");
            System.out.println("请回答!");
            int num = s.nextInt(); //接收用户输入的答案
            if (num == (number1 + number2)) {
                System.out.println("恭喜您答对了!");
                score += 10;
            }else {
                System.out.println("很遗憾答错了!");
            }
        }
        System.out.println("分数为 :"+score);
    }
}
