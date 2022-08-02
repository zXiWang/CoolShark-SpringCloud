package io;

public class CurrentTimeDemo {
    public static void main(String[] args) {
        //返回的UTC时间，1970年1月1日 00：00：00到当前系统时间之间经过的毫秒
        long ms = System.currentTimeMillis();
        System.out.println(ms);
        long max = Long.MAX_VALUE;
        System.out.println("公元:"+(max/1000/60/60/24/365+1970));

    }
}
