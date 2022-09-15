package 作业.day08;
/**
 * 修改下面的代码,使得两个线程执行dosome方法时是同步的
 * @author Xiloer
 *
 */
public class Test01 {
	public static void main(String[] args) {
		Boo boo = new Boo();
		Thread t1 = new Thread(() -> boo.dosome());
		Thread t2 = new Thread(() -> boo.dosome());
		t1.start();

		t2.start();
	}
}
class Boo{
	public  void dosome () {
		Thread t = Thread.currentThread();
		synchronized (this) {
			try {				
				System.out.println(t.getName()+":正在执行dosome方法...");
				Thread.sleep(5000);
				System.out.println(t.getName()+":执行dosome方法完毕!");
			} catch (Exception e) {
			}
		}
	}
}