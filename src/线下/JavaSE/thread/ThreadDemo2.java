package thread;

public class ThreadDemo2 extends Thread{
    public static void main(String[] args) {
        Runnable r1= new MyRunnable1();
        Runnable r2= new MyRunnable2();

        Thread t1= new Thread(r1);
        Thread t2= new Thread(r2);
        t1.start();
        t2.start();
    }

}
class MyRunnable1 implements Runnable {

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("你谁啊你");
        }
    }
}
class MyRunnable2 implements Runnable {

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("我是你爹");
        }
    }
}
