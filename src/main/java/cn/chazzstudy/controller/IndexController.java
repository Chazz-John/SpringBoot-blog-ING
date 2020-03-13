package cn.chazzstudy.controller;

import cn.chazzstudy.mapper.UserMapper;
import cn.chazzstudy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 描述：访问主页时，判断登录状态
 * @Email 2741953539@qq.com
 * @Author Chazz
 * @date 2020.03.08 18:10
 **/
@Controller
public class IndexController {
    @Autowired(required = false)
    private UserMapper userMapper;
    @GetMapping("/")
    /**
     *功能描述:响应首页访问请求
     * @param request 获取请求的request
     * @return: java.lang.String-返回index页面路径
     * @Author: Chazz
     * @Date: 2020/3/13 11:55
     */
    public String hello(HttpServletRequest request) {
        //获取页面的cookie值
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            //通过循环判断，是否存在名为"token"的cookie值
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    //如果存在token，则从数据库中查询出值token对应的user数据
                    User user = userMapper.findByToken(token);
                    if (user != null){
                        //将查询到的user数据返回到页面
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return "index";
    }
}
