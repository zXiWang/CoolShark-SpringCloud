package com.xiwang.csmall.passport;

public class test2 {
    public static void main(String[] args) {
        String str = "abccba";
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        String newStr = String.valueOf(sb);
        if (str.equals(newStr)) {
            System.out.println("是回文");
            return;
        }
        System.out.println("不是回文");
    }
}
