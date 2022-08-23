package com.webserver.core;

import com.webserver.annotatiob.Controller;
import com.webserver.annotatiob.RequestMapping;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
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
                    DispatcherServlet.class.getClassLoader()
                            .getResource(".").toURI()
            );
            File controllerDir = new File(dir, "com/webserver/controller");
            File[] controllers = controllerDir.listFiles(f -> f.getName().endsWith(".class"));
            Class cls;
            Method[] methods;
            if (controllers.length > 0) {
                System.out.println("进入处理业务..........");
                for (File fileClass : controllers) {
                    cls = Class.forName("com.webserver.controller." + fileClass.getName().substring(0, fileClass.getName().indexOf(".")));
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
            }
        } catch (URISyntaxException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       Method method= mapping.get("/regUser");
        System.out.println(method);
    }

    public static Method getMethod(String path) {
        return mapping.get(path);
    }
}
