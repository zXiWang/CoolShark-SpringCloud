package com.webserver.core;

import com.webserver.annotatiob.Controller;
import com.webserver.annotatiob.RequestMapping;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {
    private static Map<String, Method> mapping = new HashMap<>();

    static {
        initMapping();
    }

    private static void initMapping() {
        try {
            File dir = new File(
                    WebServerApplication.primarySource.getResource(".").toURI()
            );
            File controllerDir = new File(dir, "/controller");
            if(!controllerDir.exists()) {
                return;
            }
            File[] subs = controllerDir.listFiles(f -> f.getName().endsWith(".class"));
            Class cls;
            Method[] methods;
            System.out.println("进入处理业务..........");
            for (File sub : subs) {
                cls = Class.forName(WebServerApplication.primarySource.getPackage().getName() + ".controller." + sub.getName().substring(0, sub.getName().indexOf(".")));
                if (cls.isAnnotationPresent(Controller.class)) {
                    System.out.println("被Controller标注的类:" + cls.getName());
                    methods = cls.getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            System.out.println("被标记为业务的方法:" + method.getName());
                            System.out.println("业务路径一致");
                            mapping.put(method.getAnnotation(RequestMapping.class).value(), method);
                        }


                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Method method = mapping.get("/regUser");
        Class cls = method.getDeclaringClass();
        System.out.println(cls);
        System.out.println(method);
    }

    public static Method getMethod(String path) {
        return mapping.get(path);
    }
}
