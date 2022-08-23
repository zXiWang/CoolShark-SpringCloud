package com.webserver.core;


import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 与客户端完成一次HTTP的交互
 */
public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        Map<String, String> headers = new HashMap<>();
        String line = readLine();
        System.out.println("请求行:" + line);

        String method;
        String uri;
        String protocol;

        String[] data = line.split("/");
        method = data[0];
        uri = data[1];
        protocol = data[2];
        System.out.println("method:" + method);
        System.out.println("uri:" + uri);
        System.out.println("protocol:" + protocol);


        while (true) {
            line = readLine();

            if (line.trim().isEmpty()) {
                break;
            }
            String[] temp = line.split(":\\s", 2);
            headers.put(temp[0], temp[1]);
            System.out.println("消息头:" + line);
        }
        headers.forEach((k, y) -> System.out.println("名字:" + k + "\t\t值:" + y));
    }

    private String readLine() {
        StringBuilder sb = null;
        try {
            int d;
            InputStream in = socket.getInputStream();
            sb = new StringBuilder();
            char cur = 'a', pre = 'a';
            while ((d = in.read()) != -1) {
                cur = (char) d;

                if (pre == 13 && cur == 10) {
                    break;
                }
                sb.append(cur);
                pre = cur;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
