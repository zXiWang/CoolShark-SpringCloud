package day04;

import java.util.Scanner;

//成绩等级判断
public class ScoreLevel {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入成绩:");
        double score = scan.nextDouble();

        //带数(888,-45,95,85,65,40)
        if (score < 0 || score > 100) {
            System.out.println("成绩不合法");
        } else if (score >= 90) { //成绩合法
            System.out.println("A-优秀");
        } else if (score >= 80) {
            System.out.println("B-良好");
        } else if (score >= 60) {
            System.out.println("C-中等");
        } else {
            System.out.println("D-不及格");
        }

    }
}














