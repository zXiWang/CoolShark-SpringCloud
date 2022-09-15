package 作业.day06;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 改正下面程序的错误,使得程序可以正常运行
 * 并能够正常的接收客户端发送的消息
 *
 * @author Xiloer
 */
public class Server {
    private ServerSocket server;

    public Server() {
        try {
            server = new ServerSocket(8080);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            System.out.println("等待客户端连接...");
            Socket socket = server.accept();
            System.out.println("一个客户端连接了!");
            InputStream in = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            System.out.println("客户端说:" + line);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}






