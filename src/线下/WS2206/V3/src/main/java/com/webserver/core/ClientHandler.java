package com.webserver.core;


import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 与客户端完成一次HTTP的交互
 */
public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream in = socket.getInputStream();
            int d;
            StringBuilder sb = new StringBuilder();
            char cur = 'a', pre = 'a';
            while ((d = in.read()) != -1) {
                cur = (char) d;

                if (pre == 13 && cur == 10) {
                    break;
                }
                sb.append(cur);
                pre = cur;
            }
            String line = sb.toString();
            System.out.println("请求行:" + line);

            String method;
            String uri;
            String protocol;

            String[] data = line.split("\\s");
            method = data[0];
            uri = data[1];
            protocol = data[2];
            System.out.println("method:" + method);
            System.out.println("uri:" + uri);
            System.out.println("protocol:" + protocol);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
