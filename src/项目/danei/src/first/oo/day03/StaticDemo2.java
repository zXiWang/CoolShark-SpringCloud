package 项目.danei.src.first.oo.day03;

public class StaticDemo2 {
    public static void main(String[] args) {
        Doo.a = 100;
        Math.random();
    }
}

class Doo{
    public static int a = 10;
    public int b;

    Doo(){
        System.out.println("Doo构造方法");
    }

    static {
        System.out.println("Doo静态代码块");
    }

    public void action() {
        this.b = 0; //直接访问类的实例成员,因为有隐式的this传递
        Doo.a = 100; //直接访问类的静态成员,因为有隐式的类名传递
    }

    public static void m() { //静态方法
        //this.b = 10; 不可直接访问类的实例成员,因为没有隐式的this传递
        a = 100; //接访问类的静态成员,因为有隐式的类名传递
    }
}
