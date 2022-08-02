package 项目.danei.src.first.oo.day04;

/**
 * 成员内部类的测试类
 */
public class TestDemo {
    public static void main(String[] args) {
        //Baby b = new Baby(); 内部类对外不具备可见性
    }
}

class Mama{ //外部类

    private int a;

    void action() {
        Baby b = new Baby(); //外部类可以创建内部类对象
        b.test();
    }

    class Baby{ //内部类
        int a;
        void test() {
            this.a = 10; //明确访问内部类自己的属性
            Mama.this.a = 10; //隐式的写法:外部类名.this.xx
            Mama.this.action(); //内部类共享外部类的属性和方法(包含私有的)
        }
    }

}