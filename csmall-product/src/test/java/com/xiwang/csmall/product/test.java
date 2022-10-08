package com.xiwang.csmall.product;

public class test {
    public static void main(String[] args) {
        int price = 10;
        int emptyBottle = 0;
        int balance = 100;
        int sum = 0;
//        while(balance>0||emptyBottle==3){
//            if(emptyBottle==3){
//                sum++;
//                emptyBottle=1;
//                continue;
//            }
//            balance-=price;
//            emptyBottle++;
//            sum++;
//        }
        sum = balance / price;
        emptyBottle = sum;
        while (true) {
            if (emptyBottle >= 3) {
                emptyBottle -= 3;
                sum++;
                emptyBottle++;
            } else break;
        }
        System.out.println(sum);
    }
}
