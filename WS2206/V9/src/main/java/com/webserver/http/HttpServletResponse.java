package com.webserver.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServletResponse {
    private Socket socket;
    private int statusCode = 200;
    private String statusReason = "OK";

    private File contentFile;

    public HttpServletResponse(Socket socket) {
        this.socket = socket;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public File getContentFile() {
        return contentFile;
    }

    public void setContentFile(File contentFile) {
        this.contentFile = contentFile;
    }

    public void response() throws IOException {
        sendStatusLine();
        sendHeaders();
        sendContent();

    }

    private void sendStatusLine() throws IOException {


        println("HTTP/1.1 " + statusCode + " " + statusReason);
    }

    private void sendHeaders() throws IOException {
        println("Content-Type: text/html");
        //告诉浏览器正文长度(单位字节)
        println("Content-Length: " + contentFile.length());
        //单独发送个回车+换行表示响应头发送完毕
        println("");
    }

    private void sendContent() throws IOException {

        OutputStream out = socket.getOutputStream();
        //发送响应正文(index.html页面的所有数据)
        FileInputStream fis = new FileInputStream(contentFile);
        byte[] buf = new byte[1024 * 10];//10kb
        int len = 0;//记录每次实际读取的字节数
        while ((len = fis.read(buf)) != -1) {
            out.write(buf, 0, len);
        }
    }

    private void println(String line) throws IOException {
        OutputStream out = socket.getOutputStream();
        byte[] data = line.getBytes(StandardCharsets.ISO_8859_1);
        out.write(data);
        out.write(13);
        out.write(10);
    }
}
