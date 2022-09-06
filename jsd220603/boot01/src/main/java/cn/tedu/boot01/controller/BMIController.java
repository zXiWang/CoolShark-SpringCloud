package cn.tedu.boot01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BMIController {
    @RequestMapping("/bmi")
    @ResponseBody
    public String bmi(double height, double weight) {
        height /= 100;
        double bmi = weight / (height * height);
        if (bmi <= 18.4) {
            return "消瘦\tbmi为:" + bmi;
        }
        if (bmi > 18.4 && bmi < 24) {
            return "正常\tbmi为:" + bmi;
        }
        if (bmi >= 24 && bmi <= 28) {
            return "超重\tbmi为:" + bmi;
        }
        return "肥胖\tbmi为:" + bmi;
    }
}
