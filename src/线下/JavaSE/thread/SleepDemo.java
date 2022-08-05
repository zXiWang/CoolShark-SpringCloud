package thread;

import java.util.Scanner;

public class SleepDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long sleepTime;
        try {
            while ((sleepTime = scanner.nextLong() * 1000) != 0) {
                System.out.println("start");
                while (sleepTime > 0) {
                    Thread.sleep(1000);
                    sleepTime -= 1000;
                    System.out.println(sleepTime / 1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

