package thread;

/**
 * 线程的优先级
 * 线程有10个优先级，分别用整数1-10表示。其中1为最低，5为默认，10为最高优先级。
 *
 * 当线程调用start方法后就纳入到了线程调度器中被统一管理，此时只能被动的被分配时间片并发运行。线程不能
 * 主动索取时间片。通过调整线程的优先级可以最大程度的调节获取时间片的概率。
 * 线程优先级越高的线程获取时间片的次数越多。
 *
 */
public class PriorityDemo {
    public static void main(String[] args) {
        Thread min = new Thread(){
            public void run(){
                for(int i=0;i<10000;i++){
                    System.out.println("min");
                }
            }
        };
        Thread norm = new Thread(){
            public void run(){
                for(int i=0;i<10000;i++){
                    System.out.println("nor");
                }
            }
        };
        Thread max = new Thread(){
            public void run(){
                for(int i=0;i<10000;i++){
                    System.out.println("max");
                }
            }
        };
//        min.setPriority(1);
        min.setPriority(Thread.MIN_PRIORITY);//设置为最小优先级
        max.setPriority(Thread.MAX_PRIORITY);//设置为最高优先级

        min.start();
        norm.start();
        max.start();
    }
}














