package thread;

public class SyncDemo2 {
    public static void main(String[] args) {
        Shop ship1= new Shop();
        Shop ship2= new Shop();
        Thread t1 = new Thread("夏末") {
            public void run() {
                ship1.buy();
            }
        };
        Thread t2 = new Thread("老王"){
            public void run() {
                ship2.buy();
            }
        };
        t1.start();
        t2.start();
    }
}
class Shop{
    public void buy(){
         try{
             Thread t= Thread.currentThread();
             System.out.println(t.getName()+":正在挑选衣服......");
             Thread.sleep(3000);

             synchronized (this) {
                 System.out.println(t.getName()+":正在试衣服......");
                 Thread.sleep(3000);
             }


             System.out.println(t.getName()+":结账离开");
         }catch (InterruptedException e){
             e.printStackTrace();
         }
    }
}