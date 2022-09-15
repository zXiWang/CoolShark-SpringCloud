package 作业.day06;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

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
			socket = new Socket("localhost", 8080);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		try {
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
			BufferedWriter bw = new BufferedWriter(osw);
			PrintWriter pw = new PrintWriter(bw,true);

			pw.println("你好服务端!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.start();
	}
}









