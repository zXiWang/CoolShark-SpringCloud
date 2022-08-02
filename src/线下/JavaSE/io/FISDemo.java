package io;

import java.io.FileInputStream;
import java.io.IOException;

public class FISDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./fos.dat");
        System.out.println(fis.read());
        System.out.println(fis.read());
        System.out.println();
    }
}
