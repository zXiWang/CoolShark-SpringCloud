package file;

import java.io.File;

/**
 * 删除一个目录
 */
public class DeleteDirDemo {
    public static void main(String[] args) {
        //删除当前目录下的demo目录
//        File dir = new File("./demo");
        File dir = new File("./a");
        if(dir.exists()){
            /*
                delete()方法在删除目录是要求该目录必须是一个空目录
             */
            dir.delete();
            System.out.println("该目录已删除!");
        }else {
            System.out.println("该目录不存在!");
        }
    }
}
