package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WriteStringDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("fos.txt", true);
        String line = "爱你~";
        byte[] bytes = line.getBytes(StandardCharsets.UTF_8);
        fos.write(bytes);
        line = "如果你突然打了个喷嚏 ,那一定是我在想你!";
        bytes = line.getBytes(StandardCharsets.UTF_8);
        fos.write(bytes);
        System.out.println("写出完毕!");
        fos.close();
    }
}
