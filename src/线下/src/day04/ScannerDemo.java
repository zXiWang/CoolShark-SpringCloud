package day04;

import java.util.Scanner;

//Scanner的演示
public class ScannerDemo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); //2.新建一个扫描仪叫scan
        System.out.println("请输入年龄:");
        int age = scan.nextInt(); //3.用扫描仪扫描一个整数赋值给age
        System.out.println("请输入商品价格:");
        double price = scan.nextDouble(); //3.用扫描仪扫描一个小数赋值给price
        System.out.println("年龄为:" + age + "，商品价格为:" + price);

        //创建ScoreLevel类，接收用户输入的成绩score(double)，并输出
        //创建CommandBySwitch类，接收用户输入的命令command(int)，并输出
    }
}















