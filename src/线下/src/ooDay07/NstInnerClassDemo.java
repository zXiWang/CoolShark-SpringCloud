package ooday07;

//匿名内部类
public class NstInnerClassDemo {
    public static void main(String[] args) {
        //1)系统自动创建了Aoo的一个派生类，但是没有名字
        //2)为该派生类创建了一个对象，名为o1
        //  ---new Aoo(){}是在创建Aoo的派生类的对象
        //3)大括号中的为派生类的类体
        Aoo o1 = new Aoo() {
        };

        //1)系统自动创建了Aoo的一个派生类，但是没有名字
        //2)为该派生类创建了一个对象，名为o2
        //3)大括号中的为派生类的类体
        Aoo o2 = new Aoo() {
        };


        //1)系统自动创建了Boo的一个派生类，但是没有名字
        //2)为该派生类创建了一个对象，名为o3
        //3)大括号中的为派生类的类体
        Boo o3 = new Boo() {
            void show() {
                System.out.println("showshow");
            }
        };
        o3.show();


        int num = 5;
        num = 55;
        Boo o4 = new Boo() {
            void show() {
                System.out.println("showshow");
                //num = 66; //编译错误，匿名内部类中不能修改外面局部变量的值，
                //因为在此处该变量会默认为final的
            }
        };


    }
}

abstract class Boo {
    abstract void show();
}

abstract class Aoo {
}
















