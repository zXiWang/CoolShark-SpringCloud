package thread;

public class ThreadDemo1 extends Thread {
    public static void main(String[] args) {
        MyTread1 myThread1 = new MyTread1();
        MyTread2 myThread2 = new MyTread2();
        myThread1.start();
        myThread2.start();
    }
}

class MyTread1 extends Thread {
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("你是谁?");
        }
    }
}

class MyTread2 extends Thread {
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("我是你爹");
        }
    }

}