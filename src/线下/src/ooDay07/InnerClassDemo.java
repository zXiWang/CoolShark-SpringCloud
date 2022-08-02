package ooday07;

//成员内部类的演示
public class InnerClassDemo {
    public static void main(String[] args) {
        Mama m = new Mama();
        //Baby b = new Baby(); //编译错误，内部类对外不具备可见性
    }
}

class Mama { //外部类
    private String name;

    void create() {
        Baby b = new Baby();
    }

    class Baby { //内部类
        void showName() {
            System.out.println(name); //简便写法
            System.out.println(Mama.this.name); //完整写法
            //System.out.println(this.name); //编译错误，this指代当前Baby对象
        }
    }
}
























