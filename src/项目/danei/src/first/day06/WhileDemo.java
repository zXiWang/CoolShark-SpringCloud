package 项目.danei.src.first.day06;

/**
 * while循环的测试类
 */
public class WhileDemo {
    public static void main(String[] args) {
        /**
         * while 语法结构:
         * 三要素 1.循环变量的初始化  2.基于循环变量的条件  3.循环变量的改变
         *
         * 第1要素
         * while(第2要素) {
         *      //需要循环的代码块 或者叫 循环体
         *      第3要素
         * }
         *
         * 执行过程:
         *      初始化循环变量
         *      先去判断 while小括号中的条件是否成立
         *      如果成立 执行一次循环体
         *      改变循环变量
         *      再去判断 while小括号中的条件是否成立
         *      如果成立 执行一次循环体
         *      改变循环变量
         *      ...
         *      直到while小括号中条件不成立,则代码继续向后执行
         *
         *  及时上课,认真完成作业!  X5
         */
        int count = 0; //第1要素
        while (count < 5) { //第2要素
            System.out.println("及时上课,认真完成作业!");
            count++; //第3要素
        }
        System.out.println("程序继续向后执行!");

        //练习:让班级的同学跑10圈
        int num = 0;
        while (num < 10) {
            System.out.println("已经跑了"+(num+1)+"圈");
            num++;
        }
    }
}
