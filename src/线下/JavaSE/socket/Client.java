package socket;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client extends JPanel {
    private Socket socket;

    public Client() throws IOException {
        socket = new Socket("localhost", 8088);
//        176.13.14.3
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();

    }

    public class ServerHander implements Runnable {
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {

        ServerHander sr = new ServerHander();
        Thread t1 = new Thread(sr);
        t1.setDaemon(true);
        t1.start();
        System.out.println("接入成功!");
        try {
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8)), true);


            Scanner scanner = new Scanner(System.in);
            String line;
            while (!"exit".equals(line = scanner.nextLine()) && line != null) {
                pw.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(socket.getPort());
    }
}
