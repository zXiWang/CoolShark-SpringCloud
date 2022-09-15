package 作业.day05;

import java.io.*;

/**
 * 扫描指定目录中的所有.java文件，并将内容全部输出到控制台
 * <p>
 * 例如将当前项目目录下src/io目录中的所有java文件内容输出
 * 到控制台
 * <p>
 * 1:先要定位./src/io目录(哪个API用来描述目录?)
 * 2:获取该目录下的所有.java文件
 * 3:遍历每一个java文件，然后按行读取里面的每一行字符串
 * 并输出控制台
 *
 * @author Xiloer
 */
public class Test03 {
    public static void main(String[] args) {
        File[] files = new File("test/作业/day05").listFiles(f -> f.getName().endsWith(".java"));
        String line;
        try {
            for (File file : files) {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
    提示代码:
	需要用到的语句，尝试按照正确顺序将下列代码并放在main方法中完成需求，
	并在注释中标注每句话的作用，

    //【在这里标注该句代码意义】
    for(int i=0;i<subs.length;i++){

    }
    //【在这里标注该句代码意义】
    while((line = br.readLine())!=null){

    }

    //【在这里标注该句代码意义】
    File sub = subs[i];

    //【在这里标注该句代码意义】
    BufferedReader br = new BufferedReader(
           new InputStreamReader(
                  new FileInputStream(sub)
           )
    );

    //【在这里标注该句代码意义】
    File dir = new File("./src/main/java/io");

    //【在这里标注该句代码意义】
    System.out.println(line);

    //【在这里标注该句代码意义】
    String line;

    //【在这里标注该句代码意义】
    File[] subs = dir.listFiles(f->f.getName().endsWith(".java"));
 */