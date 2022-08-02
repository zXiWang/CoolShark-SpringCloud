package 项目.danei.src.first.oo.day04;

/**
 * 匿名内部类的测试类
 */
public class NsInnerClassDemo {
    public static void main(String[] args) {
        /*Boo b = new Boo();
        b.show();*/
        /**
         * 匿名内部类的实现方式 : 更关心如何实现逻辑,不关心语法如何做
         * 1.创建了Aoo的子类,只不过没有名字
         * 2.将当前这个子类对象赋值给a
         * 3.花括号就是匿名子类的类体!
         */
        int b = 10;
        //b = 20;
        Aoo a = new Aoo(){ //外部类为NsInnerClassDemo,匿名内部类的父类Aoo
            @Override
            public void show() {
                //匿名内部类中可以访问外部类的局部变量,但是无法修改
                //默认在匿名内部类中是将变量修饰为final的
                System.out.println(b);
                System.out.println("匿名内部类重写父类的show方法");
            }
        };
        a.show();

        Coo c = new Coo() {
            @Override
            void test() {

            }
        };
    }
}

class Aoo{ //父类
    public void show() {
        System.out.println("Aoo类的show方法");
    }
}

class Boo extends Aoo{ //常规实现重写的方式:1.创建类,2.继承父类,3.实现重写
    @Override
    public void show() {
        System.out.println("Boo类的重写A类的show方法");
    }
}

abstract class Coo{
    abstract void test();
}