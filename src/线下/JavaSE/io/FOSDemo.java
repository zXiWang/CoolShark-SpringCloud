package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FOSDemo {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("./fos.dat");
        fos.write(1);
        fos.write(3);

        System.out.println("写出完毕!");

    }
}
