package socket;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client extends JPanel {
    private Socket socket;

    public Client() throws IOException {
        socket = new Socket("176.13.14.3", 8088);
//        176.13.14.3
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
    }

    public void start() {
        System.out.println("接入成功!");
        try {
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8)), true);
            Scanner scanner = new Scanner(System.in);
            String line;
            while (!"exit".equals(line = scanner.nextLine())&&line!=null) {
                pw.println(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                socket.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(socket.getPort());
    }
}
