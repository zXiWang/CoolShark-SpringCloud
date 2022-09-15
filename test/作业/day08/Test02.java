package 作业.day08;

/**
 * 修改下面代码的编译
 *
 * @author Xiloer
 */
public class Test02 {
    public static void main(String[] args) {
        Foo foo = new Foo();
        Thread t1 = new Thread(() -> foo.dosome());
        Thread t2 = new Thread(() -> foo.dosome());
        t1.start();
        t2.start();
    }


}

class Foo {
    public void dosome() {
        synchronized (this) {
            System.out.println("hello!");
        }
    }
}

