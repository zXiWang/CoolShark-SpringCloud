package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {

        Class cls = Class.forName("reflect.Person");
        Object obj = cls.newInstance();
        Method[] testMethods = cls.getDeclaredMethods();
        for (Method method : testMethods) {
            if (method.getParameterCount() == 0&&method.getModifiers()== Modifier.PUBLIC) {
                System.out.println("自动调用:"+method.getName()+"()");
                method.invoke(obj);
            }
        }
    }
}
