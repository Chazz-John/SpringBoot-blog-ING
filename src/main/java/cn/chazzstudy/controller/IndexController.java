package cn.chazzstudy.controller;

import cn.chazzstudy.mapper.UserMapper;
import cn.chazzstudy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Email 2741953539@qq.com
 * @Author Chazz
 * @date 2020.03.08 18:10
 */

@Controller
public class IndexController {

    @Autowired(required = false)
    private UserMapper userMapper;
    @GetMapping("/")
    public String hello(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null){
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return "index";
    }
}
