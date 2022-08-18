package reflect;

public class ArgsDemo {
    public static void main(String[] args) {

        test();
        test("a");
        test("a","b");
        test("a","b","c");
        test("a","b","c","d");
        test("a","b","c","e");
        test("a","b","c","e","f");
    }

    public static void test(String... arg) {
        System.out.println(arg.length);
    }
}
