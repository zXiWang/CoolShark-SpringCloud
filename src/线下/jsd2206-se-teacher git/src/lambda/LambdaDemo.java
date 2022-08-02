package lambda;

import java.io.File;
import java.io.FileFilter;

/**
 * JDK8之后推出的lambda表达式
 * lambda表达式可以"面向函数式编程"
 * lambda表达式给我们的直观感受是可以用更简洁的语法创建匿名内部类
 * 当要实现的接口中【只有一个抽象方法】时，才可以使用lambda表达式去替换原始的匿名内部类形式
 * 创建
 *
 * 基本语法:
 * (参数列表)->{
 *     方法体
 * }
 *
 */
public class LambdaDemo {
    public static void main(String[] args) {
        //该过滤器的过滤条件是名字含有"t"的则接受
//        FileFilter filter = new FileFilter() {
//            public boolean accept(File file) {
//                String name = file.getName();//获取要过滤的子项的名字
//                boolean c = name.contains("t");//判断名字是否含有字母t
//                return c;//含有t的则接受(返回true)
//            }
//        };

      /*  FileFilter filter = new FileFilter() {
            public boolean accept(File file) {
                return file.getName().contains("t");
            }
        };

        FileFilter filter1 = (File file) -> {
                return file.getName().contains("t");
        };

        //参数类型可以忽略不写
        FileFilter filter2 = (file) -> {
            return file.getName().contains("t");
        };

        //如果方法体中只有一句代码时，方法体的”{}“可以忽略不写
        //如果这句代码含有return关键字，那么在忽略"{}"的同时也要一同忽略return
        FileFilter filter3 = (file) -> file.getName().contains("t");


        //如果参数列表中只有一个参数时，"()"可以忽略不写
        FileFilter filter4 = f -> f.getName().contains("t");*/



        File dir = new File("./src/file");
       /*
        File[] subs = dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.getName().contains("t");
            }
        });
        */
        File[] subs = dir.listFiles(f->f.getName().contains("t"));

    }

}
