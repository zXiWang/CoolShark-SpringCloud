package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo4 {
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
        Person p= new Person();
        p.sayHello();
        Class cls=Class.forName("reflect.Person");
        Object obj=cls.newInstance();
        Method method=cls.getMethod("sayHello");
        method.invoke(obj);
    }
}
