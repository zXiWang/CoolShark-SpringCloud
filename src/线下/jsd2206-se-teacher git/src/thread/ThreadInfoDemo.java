package thread;

/**
 * 获取线程相关信息的一组方法
 */
public class ThreadInfoDemo {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();//获取主线程
        String name = t.getName();
        System.out.println("name:"+name);

        long id = t.getId();//获取唯一标识
        System.out.println("id:"+id);

        int priority = t.getPriority();//获取线程优先级 1-10 默认值为5
        System.out.println("优先级:"+priority);

        boolean isAlive = t.isAlive();//线程是否活着
        boolean isDaemon = t.isDaemon();//是否为守护线程
        boolean isInterrupted = t.isInterrupted();//是否被中断了
        System.out.println("isAlive:" + isAlive);
        System.out.println("isDaemon:" + isDaemon);
        System.out.println("isInterrupted:" + isInterrupted);
    }
}
