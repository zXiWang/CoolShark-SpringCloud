package com.xiwang.csmall.passport;

import java.util.*;

public class Demo03 {
    public static void main(String[] args) {
        Foo foo = new Foo();
        HashMap map = new HashMap();
        map.put(null, 1);
        System.out.println(map.get(null));
        foo.test(127);
        char cr='a'+1;
        String str = "hello";
    }
}

class Foo {
    void test(byte a) {
        System.out.println("byte");
    }

    void test(int... a) {
        System.out.println("int...");
    }

    void test(short a) {
        System.out.println("short");
    }

    void test(long a) {
        System.out.println("long");
    }
}



