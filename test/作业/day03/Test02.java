package 作业.day03;

import java.io.File;
import java.io.FileFilter;

/**
 * 列出当前目录中所有名字包含s的子项。
 * <p>
 * 使用匿名内部类和lambda两种写法
 * <p>
 * 单词记一记:
 * FileFilter   文件过滤器
 * accept       接受
 *
 * @author Xiloer
 */
public class Test02 {
    public static void main(String[] args) {

        f1();
        f2();

    }

    private static void f2() {
        File[] files = new File("testfile").listFiles(
                (pathname -> pathname.getName().contains("s"))

        );
        for (File file : files) {
            System.out.println(file.getName());
        }
    }

    static void f1(){
        File[] files = new File("testfile").listFiles(
                (new FileFilter() {

                    @Override
                    public boolean accept(File pathname) {
                        return pathname.getName().contains("s");
                    }
                })

        );
        for (File file : files) {
            System.out.println(file.getName());
        }
    }
}
/*
	提示代码:
	需要用到的语句，尝试按照正确顺序将下列代码并放在main方法中完成需求，
	并在注释中标注每句话的作用，
	//【在这里标注该句代码意义】
	File dir = new File(".");

	//【在这里标注该句代码意义】
	File[] suns = dir.listFiles(new FileFilter() {
		public boolean accept(File file) {

		}
	});

	//【在这里标注该句代码意义】
	if(dir.isDirectory()){

	}

	//【在这里标注该句代码意义】
	System.out.println(subs[i].getName());

	//【在这里标注该句代码意义】
	return file.getName().contains("s");

	//【在这里标注该句代码意义】
	for(int i=0;i<subs.length;i++){

	}



 */