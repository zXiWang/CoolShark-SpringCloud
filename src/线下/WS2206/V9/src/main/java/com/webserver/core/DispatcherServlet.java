package com.webserver.core;

import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class DispatcherServlet {
    private static DispatcherServlet servlet = new DispatcherServlet();
    private static File dir;
    private static File staticDir;

    static {
        try {
            dir = new File(
                    ClientHandler.class.getClassLoader()
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

    public static DispatcherServlet getServlet() {
        return servlet;
    }


    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getUri();
        System.out.println("请求的抽象路径:" + path);

        File file = new File(staticDir, path);
        if ("/root".equals(path) || !file.exists()) {
            response.setStatusCode(404);
            response.setStatusReason("NotFound");
            path = "root/404.html";
            file = new File(staticDir, path);
            response.setContentFile(file);
        }
        response.setContentFile(file);
        response.response();
    }
}
