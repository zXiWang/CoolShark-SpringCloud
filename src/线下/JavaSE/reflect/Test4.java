package reflect;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;

public class Test4 {
    public static void main(String[] args) throws URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        File dir = new File(
                Test3.class.getResource(".").toURI()
        );
        Class cls;
        File[] files = dir.listFiles(f -> f.getName().endsWith(".class"));
        for (File file : files) {
            cls = Class.forName("reflect." + file.getName().substring(0, file.getName().indexOf(".")));
            Method[] Methods = cls.getDeclaredMethods();
            if ((cls.isAnnotationPresent(AutoRunClass.class))) {
                Object obj = cls.newInstance();
                System.out.println("注解的类:" + file.getName());
                System.out.println("实例化的" + obj);
                for (Method method : Methods) {
                    if (method.isAnnotationPresent(AutoRunMethod.class) && method.getParameterCount() == 0 && method.getModifiers() == Modifier.PUBLIC) {
                        System.out.println("自动调用:" + method.getName() + "()");
                        method.invoke(obj);
                    }
                }
            }
        }
    }
}
