package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 聊天室服务端
 */
public class Server {
    /*
        运行在服务端的ServerSocket,主要作用两个:
        1:开启服务端口,客户端就是通过这个端口与服务端建立连接的.(ServerSocket构造方法)
        2:监听该端口,一旦一个客户端连接时,就会返回一个Socket实例与其通讯.(accept()方法的作用)
     */
    private ServerSocket serverSocket;

    //老师, allOut中存入的pw是以一个什么形式存入的???

    //保存所有客户端的输出流用于广播消息
    private PrintWriter[] allOut = {};

    public Server(){
        try {
            System.out.println("正在启动服务端...");
            /*
                如果执行下面代码出现异常:
                java.net.BindException:address already in use
                原因是申请的8088端口已经被系统其它程序占用了.
             */
            serverSocket = new ServerSocket(8088);
            System.out.println("服务端启动完毕!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start(){

        try {
            while(true) {
                System.out.println("等待客户端连接...");
                Socket socket = serverSocket.accept();//阻塞方法
                System.out.println("一个客户端连接了!");
                //启动一个线程负责处理该客户端交互
                ClientHandler handler = new ClientHandler(socket);
                Thread t = new Thread(handler);
                t.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    /**
     * 该线程任务负责与指定客户端进行交互
     */
    private class ClientHandler implements Runnable{
        private Socket socket;
        private String host;//记录当前对应客户端的IP地址

        public ClientHandler(Socket socket){
            this.socket = socket;
            //通过socket获取远端计算机IP地址(获取到了客户端的)
            host = socket.getInetAddress().getHostAddress();
        }

        public void run(){
            PrintWriter pw = null;
            try {
                //通过刚接受连接的socket,获取输入流来读取该客户端发送过来的消息
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);

                //通过socket获取输出流给客户端发送消息
                OutputStream out = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                BufferedWriter bw = new BufferedWriter(osw);
                pw = new PrintWriter(bw,true);

                //将该输出流存入到共享数组allOut中
//                synchronized (this) {//不同线程调用不同的ClientHandler的run方法,因此run方法中的this(ClientHandler不是同一个)
//                synchronized (allOut) {//线程进入到同步块中会创建新的数组对象赋值给allOut,导致其如果作为锁对象会发生改变.
                synchronized (Server.this) {
                    //1将allOut数组扩容
                    allOut = Arrays.copyOf(allOut, allOut.length + 1);
                    //2将当前pw存入数组最后一个位置
                    allOut[allOut.length - 1] = pw;
                }
                sendMessage(host+"上线了,当前在线人数:"+allOut.length);


                String line;
                /*
                    使用readLine读取远端计算机发送过来消息时,对应断开操作不同,这里体现的结果不同,
                    可能存在如下情况:
                    1:当远端计算机调用socket.close(),此时对方会进行正常的TCP四次挥手断开连接.
                      那么readLine方法会返回值为null.
                    2:readLine()方法抛出异常:java.net.SocketException: connection reset
                      说明客户端是非正常退出的(没有进行TCP挥手操作)
                 */
                while((line = br.readLine())!=null) {
                    sendMessage(host + "说:" + line);
                }
            } catch (IOException e) {
//                e.printStackTrace();
            } finally{
                //处理客户端断开连接后的操作
                //将pw从数组allOut中删除 直接将数组最后一个元素 赋值给pw 然后缩容可以吗，
                synchronized (Server.this) {
                    for (int i = 0; i < allOut.length; i++) {
                        if (allOut[i] == pw) {
                            allOut[i] = allOut[allOut.length - 1];
                            allOut = Arrays.copyOf(allOut, allOut.length - 1);
                            break;
                        }
                    }
                }
                sendMessage(host+"下线了,当前在线人数:"+allOut.length);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void sendMessage(String message){
            System.out.println(message);
            synchronized (Server.this) {
                //将消息发送给所有客户端
                for (int i = 0; i < allOut.length; i++) {
                    allOut[i].println(message);
                }
            }
        }
    }

}
