import java.io.File;

public class fileDelete {
    public static void main(String[] args) {
        File[] file = new File("testfile/123").listFiles();
        for (int i = 0; i < file.length; i++) {
            delete(file[i]);
        }

    }

    public static void delete(File file) {
        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = new File(file.getPath()).listFiles();
            for (int i = 0; i < files.length; i++) {
                delete(files[i]);
            }
        }
        file.delete();

    }
}
