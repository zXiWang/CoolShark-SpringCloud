package com.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpServerRequest {
    private Socket socket;
    private String method;
    private String uri;
    private String protocol;
    private Map<String, String> headers = new HashMap<>();

    public HttpServerRequest(Socket socket) {
        this.socket = socket;

//        请求行
        parseRequestLine();

//        消息头
        parseHeaders();

//        正文
        parseContent();
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHeaders(String name) {
        return headers.get(name);
    }

    private void parseRequestLine() {

        String line = readLine();
        System.out.println("请求行:" + line);


        String[] data = line.split("\\s");
        method = data[0];
        uri = data[1];
        protocol = data[2];
        System.out.println("method:" + method);
        System.out.println("uri:" + uri);
        System.out.println("protocol:" + protocol);
    }

    private void parseHeaders() {
        while (true) {
            String line = readLine();

            if (line.trim().isEmpty()) {
                break;
            }
            String[] temp = line.split(":\\s", 2);
            headers.put(temp[0], temp[1]);
            System.out.println("消息头:" + line);
        }
        headers.forEach((k, y) -> System.out.println("名字:" + k + "\t\t值:" + y));
    }

    private void parseContent() {

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