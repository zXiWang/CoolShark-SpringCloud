package cn.tedu.boot01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class HelloController {

//    @RequestMapping("/hello")
//    public void hello(HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter pw=response.getWriter();
//        pw.println("请求成功!");
//        pw.close();
//
//    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "66666666";
    }
}
