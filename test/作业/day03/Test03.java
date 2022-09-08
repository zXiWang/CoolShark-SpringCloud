package 作业.day03;

import java.io.*;

/**
 * 将当前目录下的所有文件都复制一份，复制的文件命名为:原
 * 文件名_cp.后缀
 * 比如原文件为:test.dat
 * 复制后的文件为:test_cp.dat
 *
 * @author Xiloer
 */
public class Test03 {
    public static void main(String[] args) throws IOException {
        File[] files = new File("testfile").listFiles();
        byte[] bytes = new byte[8 * 1024];
        OutputStream out;
        for (File file : files) {
            String[] name = file.getName().split("\\.");
            if (file.isFile()) {
                InputStream in = new BufferedInputStream(new FileInputStream(file));
                System.out.println(file.getPath());
                if (name.length > 1) {
                    out = new BufferedOutputStream(
                            new FileOutputStream(
                                    "testfile/" + name[0] + "_cp." + name[1]));
                } else {
                    out = new BufferedOutputStream(
                            new FileOutputStream(
                                    "testfile/" + name[0] + "_cp"));
                }

                while (in.read(bytes) != -1) {
                    out.write(bytes, 0, bytes.length);
                }
            }

        }
    }
}


/**
 * 思路:
 * 分为几部分考虑.
 * 第一个是要获取到当前目录中的所有文件(思考哪个API可以解决)
 * 第二个是获取到的每一个文件都要复制(复制用什么API)
 * 第三个是复制的文件名，如何重新组建xxx_cp.xxx的名字?
 * 这里要将原文件名拆开后想办法拼接_cp.
 */


