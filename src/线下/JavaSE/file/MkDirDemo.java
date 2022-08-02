package file;

import java.io.File;

public class MkDirDemo {
    public static void main(String[] args) {
        File dir = new File("./demo/g/h/h");
        if (dir.exists()) {
            System.out.println("目录已存在!");

        } else {
            dir.mkdirs();
            System.out.println("该目录已创建!");
        }

    }
}
