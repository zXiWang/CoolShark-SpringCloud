package apiday01;

/**
 * static String valueOf(数据类型 a):
 * 将其它数据类型转换为String
 */
public class ValueOfDemo {
    public static void main(String[] args) {
        int a = 123;
        String s1 = String.valueOf(a); //将int型变量a转换为String类型并赋值给s1
        System.out.println(s1); //123---字符串类型

        double b = 123.456;
        String s2 = String.valueOf(b); //将double型变量b转换为String类型并赋值给s2
        System.out.println(s2); //123.456---字符串类型

        String s3 = b + ""; //任何类型与字符串相连，结果都变为字符串类型，效率低(一会讲)
        System.out.println(s3); //123.456---字符串类型
    }
}














