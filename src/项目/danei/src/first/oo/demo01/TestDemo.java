package 项目.danei.src.first.oo.demo01;

public class TestDemo {
    public static void main(String[] args) {
        Aoo aoo = new Aoo(); //实例化一个对象
        //aoo.a = 10; //在其他类中不可访问aoo类的私有变量
        //aoo.action(); //在其他类中不可访问aoo类的私有方法
        aoo.b = 20; //同包的其他类可以访问公开的成员(变量或方法)
        aoo.c = 30; //同包的其他类可以访问默认的成员(变量或方法)
        aoo.d = 40; //同包的其他类可以访问保护的成员(变量或方法)
    }
}
