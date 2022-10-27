package com.xiwang.gate.shanghai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/sh")
public class ShanghaiController {

    @GetMapping("/show")
    public String show(String username,Integer age){
        System.out.println(ZonedDateTime.now());
        return "这里是上海！";
    }


}
