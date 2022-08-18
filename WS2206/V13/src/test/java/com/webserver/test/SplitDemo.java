package com.webserver.test;

import java.util.Arrays;
import java.util.Collections;

public class SplitDemo {
    public static void main(String[] args) {
        String line="1=2=3=4=5=6=7=8=====";
        String[] split = line.split("=",-1);
        System.out.println(Arrays.toString(split));


    }
}
