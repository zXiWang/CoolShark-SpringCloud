package file;

import java.io.File;
import java.io.FileFilter;

public class LambdaDemo {
    public static void main(String[] args) {
        FileFilter filter = new FileFilter() {
            public boolean accept(File file) {
                return file.getName().contains("t");
            }
        };

        FileFilter filter2 = file2 -> file2.getName().contains("t");
    }
}
