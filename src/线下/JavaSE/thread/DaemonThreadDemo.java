package thread;

public class DaemonThreadDemo {
    public static void main(String[] args) {
        Thread rose = new Thread("rose") {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(getName() + ":let me go!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println(getName() + ":啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊!");
                System.out.println("噗通!");
            }

        };

        Thread jack = new Thread("jack") {
            public void run() {
                try {
                    while (true) {
                        System.out.println(getName() + ":you jump!I jump!");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        rose.start();
        jack.setDaemon(true);

        jack.start();

    }
}
