package cn.chazzstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description
 * @Email 2741953539@qq.com
 * @Author Chazz
 * @date 2020.03.08 18:10
 */

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "index";
    }
}
