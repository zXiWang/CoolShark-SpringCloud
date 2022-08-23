package com.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpServletRequest {
    private Socket socket;
    //请求行相关信息
    private String method;//请求方式
    private String uri;//抽象路径
    private String protocol;//协议版本

    private String requestURI;
    private String queryString;
    private Map<String, String> parameters = new HashMap<String, String>();
    //消息头相关信息
    private Map<String, String> headers = new HashMap<>();

    public HttpServletRequest(Socket socket) throws IOException, EmptyRequestException {
        this.socket = socket;
        //1解析请求行
        parseRequestLine();
        //2解析消息头
        parseHeaders();
        //3解析消息正文
        parseContent();
    }

    //解析请求行
    private void parseRequestLine() throws IOException, EmptyRequestException {
        String line = readLine();

        if (line.isEmpty()) {//若请求行是空字符串,则说明本次为空请求
            throw new EmptyRequestException("request is empty");
        }

        System.out.println("请求行:" + line);
        String[] data = line.split("\\s");
        method = data[0];
        uri = data[1];//这里可能出现数组下标越界异常ArrayIndexOutOfBoundsException,原因是浏览器的问题！！！后期我们解决。建议:浏览器测试时尽量不使用后退，前进这样的功能测试。
        protocol = data[2];

        parseURI();
        System.out.println("method:" + method);
        System.out.println("uri:" + uri);
        System.out.println("protocol:" + protocol);
    }

    private void parseURI() {
            /*
            uri有两种情况:
            1:不含有参数的
              例如: /index.html
              直接将uri的值赋值给requestURI即可.

            2:含有参数的
              例如:/regUser?username=fancq&password=&nickname=chuanqi&age=22
              将uri中"?"左侧的请求部分赋值给requestURI
              将uri中"?"右侧的参数部分赋值给queryString
              将参数部分首先按照"&"拆分出每一组参数，再将每一组参数按照"="拆分为参数名与参数值
              并将参数名作为key，参数值作为value存入到parameters中。
         */
        if (uri.split("\\?", 2).length < 2) {
            requestURI = uri;
        } else {
            requestURI = uri.split("\\?")[0];
            queryString = uri.split("\\?")[1];
            String[] temp = queryString.split("&");

            for (String t : temp) {

                parameters.put(t.split("=")[0], t.split("=",-1)[1]);
            }
        }


        System.out.println("requestURI:" + requestURI);
        System.out.println("queryString:" + queryString);
        System.out.println("parameters:" + parameters);
    }

    //解析消息头
    private void parseHeaders() throws IOException {
        //读取消息头
        while (true) {
            String line = readLine();
            if (line.isEmpty()) {
                break;
            }
            System.out.println("消息头:" + line);
            String[] data = line.split(":\\s");
            //key:Connection value:keep-alive
            headers.put(data[0], data[1]);//key:消息头的名字  value:消息头的值

        }
        System.out.println("headers:" + headers);
    }

    //解析消息正文
    private void parseContent() {
    }

    private String readLine() throws IOException {
        //当对同一个socket调用多次getInputStream方法时，获取回来的输入流始终是同一条流
        InputStream in = socket.getInputStream();
        int d;
        StringBuilder builder = new StringBuilder();
        char pre = 'a', cur = 'a';
        while ((d = in.read()) != -1) {
            cur = (char) d;
            if (pre == 13 && cur == 10) {
                break;
            }
            builder.append(cur);
            pre = cur;
        }
        return builder.toString().trim();
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

    public String getHeader(String name) {
        return headers.get(name);
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getParameters(String name) {
        return parameters.get(name);
    }
}
