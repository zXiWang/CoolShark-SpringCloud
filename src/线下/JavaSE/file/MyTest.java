package file;

import java.io.File;
import java.io.IOException;

public class MyTest {
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            File file = new File("./test" + i + ".txt");
            try {
                file.createNewFile();
                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        fileCreate();
    }

    private static void fileCreate() {
        String fileName = "test1.txt";
        File testFile = null;
        for (int i = 2; i <= 100; i++) {
            fileName = fileName.replaceAll("" + (i - 1), "" + i);
            testFile = new File(fileName);
            if (testFile.exists()) {
                testFile.delete();
                continue;
            } else {
                try {
                    testFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(testFile.getName());
        }
    }
}
