package thread;

public class PriorityDemo {
    public static void main(String[] args) {
        Thread max = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                System.out.println("max");
            }
        });
        Thread min = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                System.out.println("min");
            }
        });
        Thread nor = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                System.out.println("nor");
            }
        });

        min.setPriority(Thread.MIN_PRIORITY);
        max.setPriority(Thread.MAX_PRIORITY);

        min.start();
        nor.start();
        max.start();
    }


}
