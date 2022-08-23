package com.webserver.core;

import com.webserver.annotatiob.Controller;
import com.webserver.annotatiob.RequestMapping;
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

/**
 * 用于完成一个http交互流程中处理请求的环节工作.
 * <p>
 * 实际上这个类是Spring MVC框架提供的一个核心的类,用于和Web容器(Tomcat)整合,
 * 使得处理请求的环节可以由Spring MVC框架完成.
 */
public class DispatcherServlet {
    private static DispatcherServlet instance = new DispatcherServlet();
    private static File dir;
    private static File staticDir;

    static {
        try {
            dir = new File(
                    DispatcherServlet.class.getClassLoader()
                            .getResource(".").toURI()
            );
            //定位target/classes/static目录
            staticDir = new File(dir, "static");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private DispatcherServlet() {
    }

    public static DispatcherServlet getInstance() {
        return instance;
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {

        String path = request.getRequestURI();
        System.out.println("请求的抽象路径:" + path);
        //首先判断该请求是否为请求一个业务
        try {
            Method method = HandlerMapping.getMethod(path);
            if (method != null) {
                method.invoke(method.getDeclaringClass().newInstance(), request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = new File(staticDir, path);
        if (file.isFile()) {//浏览器请求的资源是否存在且是一个文件
            //正确响应其请求的文件
            response.setContentFile(file);
        } else {
            //响应404
            response.setStatusCode(404);
            response.setStatusReason("NotFound");
            file = new File(staticDir, "/root/404.html");
            response.setContentFile(file);
        }
    }

}


