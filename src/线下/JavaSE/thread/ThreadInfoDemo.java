package thread;

public class ThreadInfoDemo {
    public static void main(String[] args) {
        Thread t=Thread.currentThread();
        String name=t.getName();
        System.out.println("name: " + name);

        long id =t.getId();
        System.out.println("id: " + id);

        t.setPriority(3);
        int priority = t.getPriority();
        System.out.println("priority: " + priority);

        boolean isAlive = t.isAlive();
        boolean isDaemon = t.isDaemon();
        boolean isInterrupted = t.isInterrupted();
        System.out.println("isAlive: " + isAlive);
        System.out.println("isDaemon: " + isDaemon);
        System.out.println("isInterrupted: " + isInterrupted);
    }
}
