package 项目.danei.src.first.day05;

import java.util.Scanner;

public class homework {
    public static void main(String[] args) {
        Scanner i=new Scanner(System.in);
        double price;
        System.out.println("请输入金额:");
        while((price=i.nextDouble())>=0){
            if(price>=500){
                price*=0.8;
                System.out.println("应付的金额:"+price);
            }
            else {
                price*=0.9;
                System.out.println("应付的金额:"+price);
            }
        }
    }
}
