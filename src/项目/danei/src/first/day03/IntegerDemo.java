package 项目.danei.src.first.day03;

/**
 * int类型的测试类
 */
public class IntegerDemo {
    public static void main(String[] args) {
        //整数直接量默认为int类型
        int a = 100; //声明一个int类型(整数)的一个变量,变量名为a,赋值100
        int b = -2147483648;

        int c = 5;
        int d = 2;
        //整数如果运算时,结果是不会保留小数位(截断删除,不会四舍五入)
        System.out.println(c / d); //2

        /**
         * int最大值+1 ---> -2147483648
         * int最大值+1+1 ---> -2147483647
         * int最大值+1+1+1 ---> -2147483646
         */
        int number = 2147483647;
        int result = number + 1;
        System.out.println(result); //-2147483648
    }
}








