package thread;

public class ThreadDemo3 {
    public static void main(String[] args) {
        Thread t1= new MyTread1();

        Runnable r1 = () -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Thread " + i + " is running");
            }
        };
        t1.start();
        r1.run();

    }
}
