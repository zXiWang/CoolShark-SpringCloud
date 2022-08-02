package file;

import java.io.File;

public class ListFilesDemo {
    public static void main(String[] args) {
        File dir = new File("src/线下/JavaSE");
        if (dir.isDirectory()) {
            File[] subs = dir.listFiles();
            System.out.println("当前目录下共:" + subs.length + "个子项");
            for (int i = 0; i < subs.length; i++) {
                File sub = subs[i];
                System.out.println(sub.getName());
            }
        }
    }
}
