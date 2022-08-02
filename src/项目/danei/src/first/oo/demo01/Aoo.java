package 项目.danei.src.first.oo.demo01;

/**
 * 访问修饰符测试类:
 * private : 私有的,只能够在当前类中使用,其他地方不可见
 * public : 公开的,任意类中可见
 * 默认的 : 不写修饰符,本类及同包类可以访问
 * protected : 保护的,本类及子类(本包/非本包)都可以访问
 */
public class Aoo {
    private int a; //将a变量设置为私有的
    public int b; //公开的
    int c; //默认的
    protected int d; //保护的

    private void action() {
        a = 10; //当前类内部可以访问私有变量
        a = 50; //当前类内部可以访问公开变量
        c = 100; //当前类内部可以访问默认变量
        d = 200; //当前类内部可以访问保护变量
    }
}
