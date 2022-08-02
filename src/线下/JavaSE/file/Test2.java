package file;

import java.io.File;

public class Test2 {
    public static void main(String[] args) {
        File dir = new File("src/线下/JavaSE/file");
        if (dir.isDirectory()) {
            File[] subs = dir.listFiles(pathname -> pathname.getName().startsWith("D"));
            for (File sub : subs) {
                System.out.println(sub.getName());
            }
        }
    }
}
