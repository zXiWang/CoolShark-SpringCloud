package file;

import java.io.File;
import java.io.IOException;

public class CreateNewFileDemo {
    public static void main(String[] args) {
        File file = new File("test.txt");
        if (file.exists()) {
            System.out.println("文件已存在");
        } else {
            try {
                file.createNewFile();
                System.out.println(file.exists());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
