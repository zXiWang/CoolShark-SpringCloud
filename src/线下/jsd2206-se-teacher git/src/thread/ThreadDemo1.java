package thread;

/**
 * 多线程
 * 多线程可以并发执行多个代码片段.
 * 并发:多个线程宏观上是一起执行,微观上是走走停停的.
 *
 * 第一种创建线程的方式:
 * 1:继承Thread
 * 2:重写run方法
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
       Thread t1 = new MyThread1();
       Thread t2 = new MyThread2();
       //线程的启动要调用start方法,而不是直接调用run方法.
       t1.start();
       t2.start();

    }
}

/**
 *  继承线程并重写run方法的形式优点在于结构简单,利于使用匿名内部类形式创建.
 *  缺点主要有两个:
 *  1:由于java是单继承的,导致如果继承了Thread就不能再继承其它类了,这在实际开发时非常不方便.
 *  2:在定义线程同时重写run方法来定义任务,这导致线程与任务存在必然的耦合关系,不利于线程的重用.
 */
class MyThread1 extends Thread{
    public void run(){
        for(int i=0;i<1000;i++){
            System.out.println("你是谁啊?");
        }
    }
}
class MyThread2 extends Thread{
    public void run(){
        for(int i=0;i<1000;i++){
            System.out.println("开门,查水表的!");
        }
    }
}




