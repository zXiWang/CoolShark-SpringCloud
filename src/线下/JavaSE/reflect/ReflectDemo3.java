package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectDemo3 {
    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Person p = new Person("李四", 22);
        System.out.println(p);
        Class cls = Class.forName("reflect.Person");
        cls.getConstructor();

        Constructor c = cls.getConstructor(String.class,  int.class);
        Object obj = c.newInstance("王五", 33);
        System.out.println(obj);

    }
}
