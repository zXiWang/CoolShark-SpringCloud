package com.xiwang.csmall.passport;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {
    public static int num = 0;

    public static void main(String[] args) {
        String str;int n;
        str = "Cool Boy";
        n = str.indexOf("cool");
        System.out.println(n);
        int num=55;
        double i=num/2;
        System.out.println(i);
        System.out.println(4&7);
//        long start = System.currentTimeMillis();
//        String Plaintext = "1234567890";
//        String rawPassword = "1234567890";
//        String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
//        Runnable runnable = new Runnable() {
//            @Override
//          synchronized   public void run() {
//                for (int i = 0; i < 100; i++) {
//                    Thread currentThread = Thread.currentThread();
//                    System.out.println("Thread-->" + currentThread.getName() + ",第" + i + "条数据");
//                    System.out.println(new BCryptPasswordEncoder().encode(rawPassword));
//                    num++;
//                }
//                long end = System.currentTimeMillis();
//                System.out.println("运行完毕,共有:" + num + "条数据");
//                System.out.println("共花费了:" + (end - start) + "ms");
//            }
//        };
//        Thread thread1 = new Thread(runnable);
//        Thread thread2 = new Thread(runnable);
//        Thread thread3 = new Thread(runnable);
//        Thread thread4 = new Thread(runnable);
//        Thread thread5 = new Thread(runnable);
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//        thread5.start();


    }
}
