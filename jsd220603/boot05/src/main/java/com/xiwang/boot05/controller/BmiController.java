package com.xiwang.boot05.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BmiController {

    @RequestMapping("/bmi")
    public String bmi(double height,double weight) {
        height/=100;
        double bmi = weight / (height * height);
        String bmiString= String.format("%.2f", bmi);
        if (bmi <= 18.4) {
            return "消瘦\tbmi为:" + bmiString;
        }
        if (bmi > 18.4 && bmi < 24) {
            return "正常\tbmi为:" + bmiString;
        }
        if (bmi >= 24 && bmi <= 28) {
            return "超重\tbmi为:" + bmiString;
        }
        return "肥胖\tbmi为:" + bmiString;
    }
}
