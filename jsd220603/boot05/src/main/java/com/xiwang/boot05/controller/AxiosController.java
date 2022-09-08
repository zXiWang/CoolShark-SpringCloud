package com.xiwang.boot05.controller;

import com.xiwang.boot05.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AxiosController {

    @RequestMapping("/helloAxios")
    public String helloAxios(String info) {
        return "测试成功!info=" + info;
    }

    @RequestMapping("/postAxios")
    public String postAxios(@RequestBody User user) {
        return "测试成功!"+user.toString();
    }
}
