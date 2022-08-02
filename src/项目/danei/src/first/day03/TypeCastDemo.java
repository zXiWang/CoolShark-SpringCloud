package 项目.danei.src.first.day03;

/**
 * 基本数据类型转换测试类
 */
public class TypeCastDemo {
    public static void main(String[] args) {
        /*int a = 100; //没有发生自动类型转换
        //1.自动类型转换
        long b = a; //发生自动类型转换

        double c = b; //发生自动类型转换

        //2.强制类型转换
        long d = 10000000000L;
        int e = (int) d;
        System.out.println(e); //结果1410065408

        long f = 2147483647;
        int g = (int)f;
        System.out.println(g); //结果2147483647

        double h = 3.1415926;
        int i = (int)h;
        System.out.println(i); //结果3

        int a = 10;
        long b = 20L;
        long c = a + b;*/

        int a = 10;
        //byte -128 ~ 127
        byte b = 127;

        byte c = 10;
        byte d = 10;
        byte e = (byte)(c + d);

//        char f = '我';
//        char g = '你';
//        char result = (char)(f + g);
//        System.out.println((int)f);
//        System.out.println((int)g);
//        System.out.println(result);
    }
}
