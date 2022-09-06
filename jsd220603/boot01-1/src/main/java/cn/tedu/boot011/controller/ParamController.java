package cn.tedu.boot011.controller;


import cn.tedu.boot011.entity.Emp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ParamController {
    @RequestMapping("/p1")
    @ResponseBody
    public String p1(HttpServletRequest request) {
        String info=request.getParameter("info");
        return "接受到的参数"+info;
    }

    @RequestMapping("/p2")
    @ResponseBody
    public String p2(String name ,String age) {

        return "接受到的参数" + name+"\t"+age;
    }

    @RequestMapping("/p3")
    @ResponseBody
    public String p3(Emp emp) {

        return emp.getName()+"\t"+emp.getSal()+"\t"+emp.getJob();
    }
}
