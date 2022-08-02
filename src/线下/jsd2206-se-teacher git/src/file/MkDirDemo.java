package file;

import java.io.File;

/**
 * make:做
 * directory:目录(文件夹)
 *
 * 创建一个目录
 */
public class MkDirDemo {
    public static void main(String[] args) {
        //在当前目录下新建一个目录:demo
//        File dir = new File("./demo");
//        File dir = new File("demo");

        File dir = new File("./a/b/c/d/e/f");
        if(dir.exists()){
            System.out.println("该目录已存在!");
        }else{
            /*
                mkdir()创建当前File表示的目录，前提是该目录所在的目录必须存在。
                mkdirs()创建当前File表示的目录，并会将所有不存在父目录一同创建出来。
                实际开发中推荐使用mkdirs这个方法。
             */
//            dir.mkdir();
            dir.mkdirs();
            System.out.println("该目录已创建!");
        }
    }
}
