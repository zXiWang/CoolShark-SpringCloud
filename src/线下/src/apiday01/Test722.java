package apiday01;

import java.util.Random;
import java.util.Scanner;

public class Test722 {
    public static void main(String[] args) {
        String temp = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int state = 0;
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        while (state == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                int rand = random.nextInt(temp.length());
                sb.append(temp.charAt(rand));
            }
            System.out.println(sb + "\n请输入验证码:");
            if (String.valueOf(sb).equalsIgnoreCase(scanner.nextLine())) {
                System.out.println("验证码正确！");
                state = 1;
            }
        }

    }
}
