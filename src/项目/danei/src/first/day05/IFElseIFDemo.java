package 项目.danei.src.first.day05;

import java.util.Scanner;

/**
 * 多路分支的使用测试类
 */
public class IFElseIFDemo {
    public static void main(String[] args) {
        /**
         * 多路分支的语法:
         *      if(条件1) {
         *          //如果满足条件1,则执行代码块1
         *      }else if(条件2) {
         *          //如果满足条件2,则执行代码块2
         *      }else if(条件3) {
         *          //如果满足条件3,则执行代码块3
         *      }......
         *
         *  执行过程:
         *      先判断条件1,如果满足条件1,则执行代码块1,否则
         *      再判断条件2,如果满足条件2,则执行代码块2,否则
         *      再判断条件3,如果满足条件3,则执行代码块3,否则
         *      ...
         *
         *   如果对 一个内容 有多种判断条件,可以使用多路分支
         *
         *   根据用户输入的分数,来判断学生的学习等级
         *   分数 score
         *   1. 如果分数大于等于90,输出 优秀!
         *   2. 如果分数小于90,大于等于80,输出 良好!
         *   3. 如果分数小于80,大于等于70,输出 一般!
         *   4. 如果分数小于70,大于等于60,输出 及格!
         *   5. 否则,输出 不及格!
         */
        System.out.println("请输入一个成绩!");
        double score = new Scanner(System.in).nextDouble(); //接收用户输入的分数
        if(score >= 90) {
            System.out.println("优秀!!!");
        }else if (score >=80) {
            System.out.println("良好!!");
        }else if (score >=70) {
            System.out.println("一般!");
        }else if (score >=60) {
            System.out.println("及格");
        }else { //否则
            System.out.println("不及格");
        }
        /**
         * 练习:通过控制台输入,获取用户的年龄
         * 对年龄进行判断等级:
         *      1. 如果age 大于等于0 并且 小于13 输出幼年!
         *      2. 如果age 大于等于13 并且 小于 18 输出青少年!
         *      3. 如果age 大于等于18 并且 小于 35 输出青壮年!
         *      4. 如果age 大于等于35 并且 小于 60 输出中年!
         *      5. 如果age 大于等于60 并且 小于150 输出老年!
         *      否则 神仙!
         */
    }
}















