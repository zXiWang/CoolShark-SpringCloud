package 项目.danei.src.first.day03;

/**
 * double类型的测试类
 */
public class DoubleDemo {
    public static void main(String[] args) {
        double a = 3.14;
        double b = 10.1; //小数直接量后可以直接加后缀D

        double c = 3.0;
        double d = 2.9;
        System.out.println(c - d); //0.10000000000000009 可能会存在误差

        double e = 6.0;
        double f = 4.9;
        System.out.println(e - f); //1.0999999999999996 可能会存在误差

        double g = 6.0;
        double h = 1.9;
        System.out.println(g - h); //4.1

        double r = Math.round(4.5); //四舍五入
        System.out.println(r);
    }
}
