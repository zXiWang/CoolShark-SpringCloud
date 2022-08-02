package 项目.danei.src.first.oo.day03;

public class StaticDemo {
    public static void main(String[] args) {
        Coo.b = 20;
        Coo c1 = new Coo(); //实例化对象
        c1.show();
        Coo c2 = new Coo(); //实例化对象
        c2.show();
        Coo c3 = new Coo(); //实例化对象
        c3.show(); //结果是?1,3
        System.out.println(Coo.b);
        //Coo.a = 100; 实例成员变量,无法通过类名调用

    }
}

class Coo{
    public int a; //实例变量属于对象
    public static int b; //静态变量属于类

    Coo() {
        a++;
        b++;
    }

    public void show() {
        System.out.println("a的值为:"+a+",b的值为:"+b);
    }
}
