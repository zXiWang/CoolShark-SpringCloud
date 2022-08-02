package 项目.danei.src.first.day07;

import java.util.Scanner;

public class demo {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int fraction=0;
        for(int i=0;i<10;i++){
            int a= (int) (Math.random()*100);
            int b= (int) (Math.random()*100);
            System.out.println(a + "+"+ b + "= ?");
            System.out.println("请输入你的答案:");
            int temp=scanner.nextInt();
            if(temp==(a+b)){
                System.out.println("答对啦！");
                fraction+=10;
            }
            else {
                System.out.println("答错了！");
            }
        }
        System.out.println("你的分数为:"+ fraction);
    }
}
