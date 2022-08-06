package socket;

import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Server {
    private ServerSocket serverSocket;

    public PrintWriter[] allOut={};

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
            PrintWriter pw=null;
            try {

                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

                OutputStream os = socket.getOutputStream();
                pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8)),true);

                synchronized (Server.this) {
                    allOut= Arrays.copyOf(allOut, allOut.length+1);
                    allOut[allOut.length - 1] =pw;
                }

                sendMessage(host+"上线了,当前在线人数:"+allOut.length);
                String line;

                while ((line = br.readLine())!=null) {
                    sendMessage(host+"说:" + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                synchronized (Server.this){
                    for (int i = 0; i < allOut.length; i++){
                        if (allOut[i]==pw){
                            allOut[i]= allOut[allOut.length - 1];
                            allOut=Arrays.copyOf(allOut, allOut.length-1);
                            break;
                        }

                    }
                }

                sendMessage(host+"下线了,当前在线人数:"+allOut.length);
                try{
                    socket.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        private void sendMessage(String message){
            System.out.println(message);
            for (int i = 0; i < allOut.length; i++) {
                allOut[i].println(message);
            }
        }
    }

    public static void main(String[] args) {
        Server socket = new Server();
        socket.start();
    }
}
