package thread;

/**
 * 使用匿名内部类形式创建线程
 */
public class ThreadDemo3 {
    public static void main(String[] args) {
        //方式1:继承Thread重写run方法
        Thread t1 = new Thread(){
            public void run(){
                for(int i=0;i<1000;i++){
                    System.out.println("你是谁啊?");
                }
            }
        };
        //方式2:实现Runnable接口单独定义任务
//        Runnable r2 = new Runnable() {
//            public void run() {
//                for(int i=0;i<1000;i++){
//                    System.out.println("我是查水表的!");
//                }
//            }
//        };
//        Thread t2 = new Thread(r2);

        Thread t2 = new Thread(()->{
            for(int i=0;i<1000;i++){
                System.out.println("我是查水表的!");
            }
        });

        t1.start();
        t2.start();
    }
}
