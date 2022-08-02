package 项目.danei.src.first.day07;

import java.util.Scanner;

/**
 * 数组的使用测试类
 */
public class ArrayDemo {
    public static void main(String[] args) {
        //1.定义和初始化
        int[] array = new int[3]; //声明了int类型的数组,并为数组初始化3块空间
        //初始化有两种方式:
        //1>静态初始化 : 在使用数组时,明确知道数组中存储数据的内容和个数时
        int[] arr1 = {100,200,300};
        //2>动态初始化 : 在使用数组时,若不明确具体存储的数据,但是明确存储的个数时
        int[] arr2 = new int[3];
        //2.数组的使用,访问  通过下标值进行数组访问,下标值从0开始
        arr2[0] = 1000;
        arr2[1] = 2000;
        //arr2[3] = 3000; //ArrayIndexOutOfBoundsException : 数组下标越界异常

        /*System.out.println(arr2[0]);
        System.out.println(arr2[1]);
        System.out.println(arr2[2]);*/
        /*System.out.println(arr1[0]);
        System.out.println(arr1[1]);
        System.out.println(arr1[2]);*/

        /*int[] arr3 = {10,40,222,482,1,88,99,12,1000};
        //arr3.length 获取数组长度,一般应用在循环条件中
        for (int i = 0; i < arr3.length; i++) { //使用for循环遍历数据,i就是下标值
            System.out.println(arr3[i]);
        }*/

        /**
         * 练习:
         *      需求1:通过控制台输入的方式,录入10名同学成绩,存储在数组中
         *      int[] scores = new int[10];
         *      例如: 请输入第1名同学的成绩
         *            XX
         *            请输入第2名同学的成绩
         *            XX
         *            请输入第3名同学的成绩
         *            XX
         *            请输入第4名同学的成绩
         *            XX
         *            ...
         *            请输入第10名同学的成绩
         *            XX
         *            成绩录入完成!
         *       需求2:计算当前班级的平均成绩,总成绩/人数
         */
        int[] scores = new int[10]; //声明一个scores数组,里面开辟了10块空间
        Scanner scanner = new Scanner(System.in); //创建扫描器
        int sum = 0; //用来计算总分数
        for (int i = 0; i < scores.length; i++){
            System.out.println("请录入第"+(i+1)+"名同学的成绩");
            int currentScore = scanner.nextInt();
            scores[i] = currentScore; //将当前成绩存放到数组中
            sum += currentScore; //累加录入
        }
        System.out.println("录入完毕,所有成绩如下");
        for (int i = 0;i < scores.length; i++) {
            System.out.println(scores[i]);
        }
        //需求2:计算当前班级的平均成绩,总成绩/人数
        System.out.println("平均成绩为:" + sum / scores.length);
    }
}
