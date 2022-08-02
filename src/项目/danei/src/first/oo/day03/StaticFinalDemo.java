package 项目.danei.src.first.oo.day03;

/**
 * 常量的使用测试类
 */
public class StaticFinalDemo {
    public static void main(String[] args) {
        //编译期间,会自动将使用的常量转换为具体的值
        //Eoo.C = 2; 常量不可修改值
        //System.out.println(Eoo.C); //通过类名调用访问常量

        //1.访问静态变量时,如果Eoo没有被加载,则进行加载.class字节码文件
        //2.获取方法区中a的值
        System.out.println(Eoo.a); //通过类名调用访问静态变量
    }
}
class Eoo{
    public static int a ; //静态变量
    public final int b = 10; //final修饰的变量需要赋值
    public static final int C = 300; //常量
    static {
        System.out.println("1");
    }
}