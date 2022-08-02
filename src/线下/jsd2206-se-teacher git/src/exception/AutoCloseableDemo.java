package exception;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * JDK7之后推出了一个新的特性:自动关闭特性
 *
 */
public class AutoCloseableDemo {
    public static void main(String[] args) {
        /*
            自动关闭特性是编译器认可的,而非虚拟机.编译器在编译的时候回将代码改为FinallyDemo2的样子
         */
        try(
                //只有实现了AutoCloseable接口的类才可以在try()中定义.JAVA IO中所有的流均实现了该接口
                FileOutputStream fos = new FileOutputStream("fos.dat");
        ){
            int d = 1;
            fos.write(d);
        }catch(IOException e){
            System.out.println("出错了");
        }
    }
}
