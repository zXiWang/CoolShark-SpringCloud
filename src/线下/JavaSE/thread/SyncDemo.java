package thread;

@SuppressWarnings("InfiniteLoopStatement")
public class SyncDemo {
    public static void main(String[] args) {
        Table table = new Table();
        Thread t1 = new Thread() {
            public void run() {

                while (true) {
                    int bean =table.getBeans();
                    Thread.yield();
                    System.out.println(getName()+":"+bean);
                }

            }
        };

        Thread t2 = new Thread() {
            public void run() {
                while (true) {
                    int bean =table.getBeans();
                    Thread.yield();
                    System.out.println(getName()+":"+bean);
                }
            }
        };
        t1.start();
        t2.start();
    }
}

class Table {
    private int beans = 20;

    public synchronized int getBeans() {
        if (beans == 0) {
            throw new RuntimeException(("没有豆子了!"));
        }
        return beans--;
    }
}