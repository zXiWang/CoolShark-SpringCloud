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
//            http://localhost:8088/index.html
            int d;
            while ((d = in.read()) != -1) {
                System.out.print((char) d);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
