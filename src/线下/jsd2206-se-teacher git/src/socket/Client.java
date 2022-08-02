package socket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    private Socket socket;//套接字
    public Client(){
        /*
            Socket实例化时需要传入两个参数
            参数1:服务端的地址信息
            参数2:服务端打开的服务端口
            客户端通过服务端的地址找到网络上的服务器计算机,通过端口可以连接上该机器上运行的服务端
            应用程序.
         */
        try {
            System.out.println("正在连接服务端...");
            socket = new Socket("localhost",8088);
            System.out.println("与服务端建立连接!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start(){

        //客户端向服务端发送数据,则需要使用socket获取输出流
        try {
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osw);
            PrintWriter pw = new PrintWriter(bw,true);
            pw.println("你好服务端!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

}
