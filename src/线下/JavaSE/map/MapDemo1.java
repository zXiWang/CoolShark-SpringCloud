package map;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MapDemo1 {
    public static void test(String msg, String csn) throws IOException {
        try (
                FileOutputStream fos = new FileOutputStream("./test/demo.txt");
        ) {
            try {
                fos.write(msg.getBytes(csn));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException | FileNotFoundException e) {
            System.out.println("出错了!");
        }
    }

        public static void main (String[]args){
            try {
                test(null,"1");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}




