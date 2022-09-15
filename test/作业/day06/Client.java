package 作业.day06;

import java.net.Socket;

/**
 * 改正下面程序的错误,使得程序可以正常运行
 * 并能够正常的给服务端发送消息
 * @author Xiloer
 *
 */
public class Client {
	private Socket socket;
	
	public Client() {
		try {
			Socket socket = new Socket("localost",8088);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		try {
//			OutputStream out = socket.getOutputStream();
//			OutputStreamWriter osw = new OutputStream(out,"YTF-8");
//			BufferedWriter bw = new BufferedWriter(osw);
//			PrintWriter pw = new PrintWriter(bw);
//			
//			pw.print("你好服务端!");
//			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void mian(String[] args) {
		Client client = new Client();
		client.start();
	}
}









