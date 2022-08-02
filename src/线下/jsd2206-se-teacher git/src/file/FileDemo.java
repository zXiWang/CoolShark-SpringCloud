package file;

import java.io.File;

/**
 * java.io.File
 * File可以表示硬盘上的一个文件或目录(本质保存的是一个抽象路径)
 * File可以:
 * 1:访问其表示的文件或目录的属性
 * 2:创建删除文件或空目录
 * 3:访问一个目录中的子项
 * File不可以:
 * 访问文件数据
 */
public class FileDemo {
    public static void main(String[] args) {
        //绝对路径的优点是清晰明了，但是缺点是路径写死了，跨平台行不好。
//        File file = new File("c:/Users/X/IdeaProjects/JSD2206_SE/demo.txt");
        //相对路径的优点是有良好的跨平台性。
        //“./”表示当前目录，在IDEA中当前目录指的是当前程序所在的项目目录
        File file = new File("./demo.txt");

        //获取file表示的文件或目录的名字
        String name = file.getName();
        System.out.println(name);

        //获取当前file表示的文件的大小(单位是字节)
        long len = file.length();
        System.out.println("大小:"+len+"个字节");

        /*
            can:能
            write:写
            read:读
         */
        boolean cw = file.canWrite();//是否可写
        boolean cr = file.canRead();//是否可读
        System.out.println("是否可写:"+cw);
        System.out.println("是否可读:"+cr);
    }
}
