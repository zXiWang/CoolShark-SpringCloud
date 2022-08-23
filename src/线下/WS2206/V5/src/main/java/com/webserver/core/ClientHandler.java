package com.webserver.core;


import com.webserver.http.HttpServerRequest;

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
        HttpServerRequest request = new HttpServerRequest(socket);

        String path = request.getUri();
        System.out.println("请求的抽象路径:" + path);
    }


}
