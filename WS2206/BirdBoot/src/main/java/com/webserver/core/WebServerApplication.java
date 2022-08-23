package com.webserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WebServer web容器
 * 用于实现Tomcat基本功能。
 */
public class WebServerApplication {
//    启动当前Boot项目的启动类的类对象
    protected static Class primarySource;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;

    public WebServerApplication() {
        try {

            System.out.println("正在启动服务端...");
            serverSocket = new ServerSocket(8088);
            threadPool = Executors.newFixedThreadPool(50);
            System.out.println("服务端启动完毕!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            while (true) {
                System.out.println("等待客户端连接...");
                Socket socket = serverSocket.accept();
                System.out.println("一个客户端连接了!");
                ClientHandler handler = new ClientHandler(socket);
                Thread t = new Thread(handler);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void run(Class primarySource, String[] args) {
        WebServerApplication.primarySource = primarySource;
        WebServerApplication server = new WebServerApplication();
        server.start();
    }
}
