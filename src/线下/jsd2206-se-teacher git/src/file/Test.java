package file;

import java.io.File;
import java.io.IOException;

/**
 * 在当前目录下新建100个文件，命名为:test1.txt---test100.txt
 */
public class Test {
    public static void main(String[] args) throws IOException {
        for(int i=1;i<=100;i++){
            File file = new File("./test"+i+".txt");
//            file.createNewFile();
            file.delete();
        }
//        System.out.println("创建完毕!");
        System.out.println("删除完毕!");
    }
}
