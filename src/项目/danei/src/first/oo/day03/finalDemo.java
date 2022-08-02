package 项目.danei.src.first.oo.day03;

/**
 * final关键字的测试类
 */
public class finalDemo {
    private int a = 20; //普通的成员变量
    private final int b = 30; //常量需要初始化赋值

    void main() {
        a = 30; //可以修改的
        //b = 40; final修饰的变量不可再次修改
    }
}

class Aoo{
    final void show() {

    }
}

class Boo extends Aoo { //被final修饰的类,无法被继承
    /*@Override
    void show() { 子类无法重写父类被final修饰的方法

    }*/
}