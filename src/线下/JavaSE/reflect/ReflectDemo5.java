package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo5 {
    public static void main(String[] args) {
        try {
            Class cls = Class.forName("reflect.Person");
            Object obj = cls.newInstance();

            Method m1 = cls.getMethod("say", String.class);
            m1.invoke(obj, "你好");

            Method m2 = cls.getMethod("say", String.class, int.class);
            m2.invoke(obj, "呵呵", 5);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
