package 项目.danei.src.first.oo.demo02;


import 项目.danei.src.first.oo.demo01.Aoo;

public class TestDemo2 extends Aoo {
    public static void main(String[] args) {
        Aoo aoo = new Aoo(); //快捷导包 : alt + enter
        aoo.b = 60; //不同包的其他类可访问公开的成员(变量/方法)
        //aoo.c = 40; 不同包的类不可访问默认的成员(变量/方法)
        //aoo.d = 50; 不同包的类不可访问保护的成员(变量/方法)
    }

    void test() {
        d = 10;
    }
}
