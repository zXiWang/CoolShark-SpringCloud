package lambda;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        File testFile = new File(".");
        if (testFile.isDirectory()) {
            File[] files = testFile.listFiles(f -> f.getName().contains("o"));
            for (File file : files
            ) {
                System.out.println(file.getName());
            }
        }
    }
}
