package 项目.danei.src.first.oo.day02;

/**
 * super关键字的使用测试类
 */
public class SuperDemo {
    public static void main(String[] args) {
        B b = new B();
    }
}

class A {
    A(int a) {

    }
    A(int a, int b) {

    }
}

class B extends A{
    B() {
        super(10); //明确调用父类的有参构造方法
        //super(); 默认报错,父类中并未提供无参构造方法
        System.out.println("这是B类中的无参构造");
    }
}