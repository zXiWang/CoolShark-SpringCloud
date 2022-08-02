package day0723;

import java.util.Locale;

public class Test01 {
    public static void main(String[] args) {
        String str = "Hello world";
        String str1 = "  Hello  ";
        test1(str);
        test2(str);
        test3(str);
        test4(str);
        test5(str);
        test6(str1);
        test7(str);
        test8(str);
        test9(str);

    }

    private static void test9(String str) {
        System.out.println(str.toUpperCase(Locale.ROOT) + str.toLowerCase(Locale.ROOT));
    }

    private static void test8(String str) {
        System.out.println(str.startsWith("H") && str.endsWith("ld"));
    }

    private static void test7(String str) {
        System.out.println(str.charAt(6));
    }

    private static void test6(String str1) {
        System.out.println(str1.trim());
    }

    private static void test5(String str) {
        System.out.println(str.substring(6));
    }

    private static void test4(String str) {
        System.out.println(str.substring(0, 5));
    }

    private static void test3(String str) {
        System.out.println(str.indexOf("o", 5));
    }

    private static void test2(String str) {
        System.out.println(str.indexOf("o"));
    }

    private static void test1(String str) {
        System.out.println(str.length());
    }


}
