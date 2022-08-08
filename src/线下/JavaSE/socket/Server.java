package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

public class Server {
    private ServerSocket serverSocket;

    //    public PrintWriter[] allOut={};
    private Collection<PrintWriter> allOut = new ArrayList<>();

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
                Thread t = new Thread(handler);
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
            host = socket.getInetAddress().getHostAddress();
        }

        @Override
        public void run() {
            PrintWriter pw = null;
            try {

                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

                OutputStream os = socket.getOutputStream();
                pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8)), true);

                synchronized (Server.this) {
//                    allOut= Arrays.copyOf(allOut, allOut.length+1);
//                    allOut[allOut.length - 1] =pw;
                    allOut.add(pw);
                }

                sendMessage(host + "上线了,当前在线人数:" + allOut.size());
                String line;

                while ((line = br.readLine()) != null) {
                    sendMessage(host + "说:" + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                synchronized (allOut) {
//                    for (int i = 0; i < allOut.size(); i++){
//                            allOut[i]= allOut[allOut.length - 1];
//                            allOut=Arrays.copyOf(allOut, allOut.length-1);
//                            break;
                }
                allOut.remove(pw);
            }

            sendMessage(host + "下线了,当前在线人数:" + allOut.size());
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(String message) {
        System.out.println(message);
        synchronized (allOut) {
            for (PrintWriter pw : allOut) {
                pw.println(message);
            }
        }

    }

    public static void main(String[] args) {
        Server socket = new Server();
        socket.start();
    }
}
