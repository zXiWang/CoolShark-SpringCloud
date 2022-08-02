package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ISRDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("src/线下/JavaSE/io/OSWDemo.java");
        InputStreamReader isr = new InputStreamReader(fis);
        int d;
        while ((d = isr.read()) != -1) {
            char c = (char) d;
            System.out.print(c);
        }
        isr.close();
    }
}
