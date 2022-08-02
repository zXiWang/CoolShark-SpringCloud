package socket;

import cn.tedu.submarine.World;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client extends JPanel {
    private Socket socket;

    public Client() throws IOException {
        socket=new Socket("localhost",8088);
//        176.13.14.3
    }

    public void start(){
        System.out.println("接入成功!");
        try {
            OutputStream os=socket.getOutputStream();
            PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8)),true);
            Scanner scanner=new Scanner(System.in);
            String line;
            while ((line=scanner.nextLine()) != null) {
                pw.println(line);
                JFrame frame = new JFrame(); //创建窗口
                setFocusable(true); //可聚焦的
                frame.add(this); //把面板添加到窗口中
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口结束程序
                frame.setSize(WIDTH, HEIGHT); //窗口尺寸
                frame.setLocationRelativeTo(null); //窗口居中呈现
                frame.setVisible(true); //可视化
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(socket.getPort());
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
    }
}
