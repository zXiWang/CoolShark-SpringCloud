package 项目.danei.src.first.day08;

/**
 * 有参数有返回值的方法使用测试类
 */
public class MethodDemo04 {
    public static void main(String[] args) {
        /**
         * 需求是多变的.
         * 做一个代码计算,计算 50 + 50 的代码.
         * 做一个代码计算,计算 1000 + 10000 的代码.
         * 做一个代码计算,计算 555555 + 666666 的代码.
         * 做一个代码计算,计算 5.5 + 7.5 的代码
         * 做一个代码计算,计算 50 + 50 + 50 的代码
         * 现在可以看出 运算方式是一样的!都是加法,但是相加的数据不一样!
         */
        int result = sum(50,50);
        System.out.println(result);
        double r = sum(5.5, 7.5);
        System.out.println(r);
        int i = sum(50, 50, 50);
        //println 本质上也是实现了重载,实现和打印出不同的数据
        System.out.println(i);
    }

    //方法的存在就是一劳永逸!
    //语法: 返回值类型 方法名() {}
    static int sum(int a,int b) {
        return a + b; //返回a,b的和
    }

    static double sum(double a,double b) {
        return a + b;
    }

    static int sum(int a,int b,int c) {
        return a + b + c; //返回a,b的和
    }
}
