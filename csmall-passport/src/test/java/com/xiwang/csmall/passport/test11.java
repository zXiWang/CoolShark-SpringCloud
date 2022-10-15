package com.xiwang.csmall.passport;

public class test11 {
    public static void main(String[] args) {
        int temp = 1;
        int SwitchSymbol = 0;
        int i=3;
        while (i <= 101) {
            switch (SwitchSymbol) {
                case 0:
                    temp = temp - i;
                    SwitchSymbol = 1;
                    break;
                case 1:
                    temp = temp + i;
                    SwitchSymbol = 0;
                    break;
            }
            i+=2;
        }
        System.out.println(temp);
    }
}
