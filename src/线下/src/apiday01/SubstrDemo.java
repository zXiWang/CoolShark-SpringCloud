package apiday01;

public class SubstrDemo {
    public static void main(String[] args) {
        String str = "www.tedu.cn";
        String name = str.substring(str.indexOf(".") + 1, str.lastIndexOf("."));
        System.out.println(name);
        System.out.println(str.substring(4));
    }
}
