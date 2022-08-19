package reflect;

import java.lang.reflect.Method;

public class ReflectDemo8 {
    public static void main(String[] args) {
        Method method = null;
        try {
            Class cls= Class.forName("reflect.Person");
            Object obj= cls.newInstance();
            method = cls.getDeclaredMethod("sayHello");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if(method.isAnnotationPresent(AutoRunMethod.class)){
            AutoRunMethod arm=method.getAnnotation(AutoRunMethod.class);
            int d=arm.value();
            System.out.println(d);
        }
    }
}
