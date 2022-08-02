package file;

import java.io.File;
import java.io.FileFilter;

/**
 *  重载的listFiles方法
 *  File[] listFiles(FileFilter filter)
 *  该方法允许我们传入一个文件过滤器，然后将当前File对象表示的目录中所有满足此过滤器要求的
 *  子项返回。
 */
public class ListFilesDemo2 {
    public static void main(String[] args) {
        /*
            获取./src/file目录中所有名字中含有“t”的文件
         */
        File dir = new File("./src/file");
        if(dir.isDirectory()){
            //使用匿名内部类创建一个文件过滤器
            FileFilter filter = new FileFilter() {
                /*
                    重写accept方法(accept:接受)
                    此方法的重写原则是定义过滤器过滤File的条件。当参数file符合此过滤器
                    的过滤条件时，方法应当返回为true。表达过滤器接受该file。
                 */
                public boolean accept(File file) {
                    String name = file.getName();
//                    return name.indexOf("t")>=0;
                    return name.contains("t");//contains:包含
                }
            };
            File[] subs = dir.listFiles(filter);
            for(int i=0;i<subs.length;i++){
                System.out.println(subs[i].getName());
            }
        }
    }
}
