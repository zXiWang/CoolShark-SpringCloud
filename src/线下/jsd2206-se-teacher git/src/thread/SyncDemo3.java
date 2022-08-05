package thread;

/**
 * 静态方法上使用synchronized,那么该方法一定具有同步效果.
 */
public class SyncDemo3 {
    public static void main(String[] args) {
        /*
            静态方法上使用synchronized后,所对象不可选,指定的是当前类的类对象.
            即:Class实例
            JVM中每一个被加载的类都有且只有一个Class实例与之对应.
         */
        Thread t1 = new Thread(){
            public void run(){
                Foo.doSomething();
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                Foo.doSomething();
            }
        };
        t1.start();
        t2.start();
    }
}
class Foo{
//    public synchronized static void doSomething(){
    public static void doSomething(){
        synchronized (Foo.class) {
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + ":正在执行dosome方法...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
            System.out.println(t.getName() + ":执行dosome方法完毕!");
        }
    }
}




