package day0723;

import java.util.Scanner;

public class Test05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(recursionCheck(str));
//        check(scanner);
    }

    private static void check(Scanner scanner) {
        String str = scanner.nextLine();
        System.out.println("输入字符串:");
        for (int i = 0; i < str.length() / 2 + 1; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                System.out.println("不是回文!");
                return;
            }
        }
        System.out.println("是回文!");
    }

    private static Boolean recursionCheck(String str) {
        if (str.length() < 2) return true;
        if (str.charAt(0) == str.charAt(str.length() - 1)) {
            str = str.substring(1, str.length() - 1);
            return recursionCheck(str);
        }
        return false;
    }
}
