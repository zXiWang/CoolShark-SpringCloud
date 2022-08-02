package io;

import java.io.*;

public class CopyDemo3 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("image.jpg");
        BufferedInputStream bis = new BufferedInputStream(fis);

        FileOutputStream fos = new FileOutputStream("image_cp.jpg");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int len = 0;
        long start = System.currentTimeMillis();
        while ((len = bis.read()) != -1) {
            bos.write(len);
        }
        long end = System.currentTimeMillis();
        System.out.println("Finished " + (end - start) + " ms");
    }
}
