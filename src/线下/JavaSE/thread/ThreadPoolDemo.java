package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            Runnable r = new Runnable() {
                public void run() {
                    Thread t = Thread.currentThread();
                    System.out.println(t.getName() + ":正在执行任务......");
                    try{
                        Thread.sleep(5000);
                    }catch (InterruptedException e) {

                    }
                    System.out.println(t.getName() + ":任务执行完毕!");
                    threadPool.shutdownNow();
                }
            };
            threadPool.execute(r);
            System.out.println("指派了一个任务给线程池");
        }

    }
}
