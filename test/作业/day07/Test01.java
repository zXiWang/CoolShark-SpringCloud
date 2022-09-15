package 作业.day07;
/**
 * 改正下面程序的错误,使得程序可以正常运行
 * 
 * 使用第二种创建线程方式创建一条线程，并输出100次:你好
 * @author Xiloer
 *
 */
public class Test01 {
	public static void main(String[] args) {
		MyRunnable run = new MyRunnable();
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0;i<100;i++) {
					System.out.println("你好!");
				}
			}
		});
		t.start();
	}
}

class MyRunnable{
	public void run() {
		for(int i=0;i<100;i++) {
			System.out.println("你好!");
		}
	}
}




