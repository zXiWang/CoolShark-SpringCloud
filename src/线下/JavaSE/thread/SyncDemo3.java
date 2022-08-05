package thread;
/**
 * 静态方法上使用synchronized,那么该方法一定具有同步效果.(排队执行)
 */
public class SyncDemo3 {
    public static void main(String[] args) {
        Foo f1 = new Foo();
        Foo f2 = new Foo();
        Thread t1 = new Thread(){
            public void run(){
                f1.doSomething();
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                f2.doSomething();
            }
        };
        t1.start();
        t2.start();
    }
}

class Foo{
    public synchronized static void doSomething(){
        synchronized (Foo.class) {
            Thread t = Thread.currentThread();
            System.out.println(t.getName()+":正在执行dosome方法...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
            System.out.println(t.getName()+":执行dosome方法完毕!");
        }


    }
}




