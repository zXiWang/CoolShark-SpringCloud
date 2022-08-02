package file;

import java.io.File;
import java.io.FileFilter;

/**
 * The type List files demo 2.
 */
public class ListFilesDemo2 {
    public static void main(String[] args) {
        File dir = new File("src/线下/JavaSE/file");

        if (dir.isDirectory()) {
            FileFilter filter = new FileFilter() {
                public boolean accept(File pathname) {
                    String name = pathname.getName();
                    return name.contains("t");
                }
            };
            File[] subs = dir.listFiles(filter);
            for (int i = 0; i < subs.length; i++) {

                System.out.println(subs[i].getName());
            }
        }

    }
}
