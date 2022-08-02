package 项目.danei.src.first.oo.demo02;


import 项目.danei.src.first.oo.demo01.Aoo;

public class Coo extends Aoo {
    void test() {
        d = 50; //不同包的子类可以访问保护的成员(属性/方法)
        Aoo a = new Aoo();
    }
}
