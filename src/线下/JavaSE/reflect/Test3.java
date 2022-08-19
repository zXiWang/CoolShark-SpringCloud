package reflect;

import java.io.File;
import java.net.URISyntaxException;

public class Test3 {
    public static void main(String[] args) throws URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        File dir = new File(
                Test3.class.getResource(".").toURI()
        );
        Class cls;
        File[] files = dir.listFiles(f -> f.getName().endsWith(".class"));
        for (File file : files) {
            cls = Class.forName("reflect." + file.getName().substring(0, file.getName().indexOf(".")));
            if (cls.isAnnotationPresent(AutoRunClass.class)) {
                System.out.println("注解的类:" + file.getName());
                Object obj = cls.newInstance();
                System.out.println("实例化的"+obj);
            }
        }
    }
}
