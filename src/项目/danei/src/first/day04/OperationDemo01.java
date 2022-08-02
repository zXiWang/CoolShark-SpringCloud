package 项目.danei.src.first.day04;

/**
 * 数学运算符的使用测试类
 */
public class OperationDemo01 {
    public static void main(String[] args) {
        int a = 10;
        int b = 5;
        System.out.println(a + b);
        System.out.println(a - b);
        System.out.println(a * b);
        System.out.println(a / b);
        //a取余b,就是取a除b的余数
        System.out.println(a % b); //0
        System.out.println(5 % 2); //1
        //取余操作时,若左边的数据小于右边的数据,余数则为它本身(左边数据)
        System.out.println(1 % 3);
        System.out.println(2 % 3);
        System.out.println(3 % 3);
    }
}
