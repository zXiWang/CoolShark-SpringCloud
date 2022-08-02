package day0709;

import java.util.Arrays;
import java.util.Scanner;

public class day0709 {
    public static void main(String[] args) {
//        Multiplication();
//        NumberOfDaffodils();
//        BallFall();
//        SortNumber();
//        MonkeyEat();
    }

    private static void MonkeyEat() {
        int number = 1;
        for (int i = 1; i < 10; i++) {
            number = (number + 1) * 2;
        }
        System.out.println(number);
    }

    private static void SortNumber() {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[3];
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        Arrays.sort(array);
        for (int i :
                array) {
            System.out.print(i + " ");
        }
    }

    private static void BallFall() {
        double height = 100.0;
        double distance = 0.0;
        int number = 10;
        for (int i = 0; i < number; i++) {
            distance += height;
            height /= 2.0;
            distance += height;
        }
        System.out.println("共经过:" + String.format("%.2f", (distance - height)) + "米，第10次反弹:" + String.format("%.2f", height) + "米");
    }

    private static void NumberOfDaffodils() {
        System.out.println("水仙花有：");
        for (int i = 100; i < 1000; i++) {
            int a = i / 100;
            int b = i / 10 % 10;
            int c = i % 10;
            if (Math.pow(a, 3) + Math.pow(b, 3) + Math.pow(c, 3) == i) {
                System.out.print(i + " ");
            }
        }
    }

    private static void Multiplication() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10 - i; j++) {
                System.out.print('\t');
            }
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "×" + i + "=" + i * j);
                if (j != i) {
                    System.out.print(" ");
                } else System.out.println();
            }
        }
    }

}
