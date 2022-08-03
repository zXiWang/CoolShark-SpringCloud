package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    private ServerSocket serverSocket;

    public Server() {
        try {
            System.out.println("正在启动服务端......");
            serverSocket = new ServerSocket(8088);
            System.out.println("服务端启动完毕!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            while (true) {
                System.out.println("等待客户端连接......");
                Socket socket = serverSocket.accept();
                System.out.println("一个客户端连接了!");
                ClientMandler handler = new ClientMandler(socket);
                Thread t=new Thread(handler);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientMandler implements Runnable {
        private String host;
        private Socket socket;
        public ClientMandler(Socket socket) {
            this.socket = socket;
            host=socket.getInetAddress().getHostAddress();
        }

        @Override
        public void run() {
            try {

                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String line;

                while (!"exit".equals(line = br.readLine())) {
                    System.out.println(host+"说:" + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server socket = new Server();
        socket.start();
    }
}
