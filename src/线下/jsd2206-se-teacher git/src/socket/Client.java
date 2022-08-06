package socket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    /*
        Socket封装了TCP协议的通讯细节,使用它就可以与远端计算机建立TCP连接.并基于两个流(一个输入
        一个输出)与远端计算机进行双向通讯
        将Socket比喻为是一部手机
     */
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
        //将读取来自服务端消息的线程启动起来
        ServerHandler serverHandler = new ServerHandler();
        Thread t = new Thread(serverHandler);
        t.setDaemon(true);
        t.start();


        //客户端向服务端发送数据,则需要使用socket获取输出流
        try{
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osw);
            PrintWriter pw = new PrintWriter(bw,true);
            Scanner scanner = new Scanner(System.in);
            while(true) {
                String line = scanner.nextLine();
                if("exit".equals(line)){
                    break;
                }
                pw.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                /*
                    Socket的close方法里封装了与服务端4次挥手操作.与服务端断开连接.
                    并且该close还会在内部将通过socket获取的输入流与输出流关闭.
                 */
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

    private class ServerHandler implements Runnable{
        public void run(){
            try {
                //通过socket获取输入流,用于读物服务端发送过来的消息
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);

                String line;
                while((line = br.readLine())!=null){
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
