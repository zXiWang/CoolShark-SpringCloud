package reflect;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;

public class Test2 {
    public static void main(String[] args) throws URISyntaxException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        File dir = new File(
                Test2.class.getResource(".").toURI()
        );
        Class cls;
        System.out.println(dir);
        File[] files = dir.listFiles(f -> f.getName().endsWith(".class"));
        for (File file : files) {
//            System.out.println(file.getName());
            cls = Class.forName("reflect." + file.getName().substring(0, file.getName().indexOf(".")));
            Object obj = cls.newInstance();
            Method[] Methods = cls.getDeclaredMethods();
            for (Method method : Methods) {

                if (method.getParameterCount() == 0 && method.getModifiers() == Modifier.PUBLIC) {
                    System.out.println("实例化的类:"+file.getName()+"\t实例化的方法:"+method.getName());
                    method.invoke(obj);
                }
            }


        }
    }
}
