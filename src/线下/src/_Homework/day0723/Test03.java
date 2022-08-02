package day0723;

import java.util.Scanner;

public class Test03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入邮箱:");
        String str = scanner.nextLine();
        System.out.println(str.substring(0, str.indexOf("@")));
    }
}
