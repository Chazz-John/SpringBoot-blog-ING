package cn.chazzstudy.controller;

import cn.chazzstudy.dto.PaginationDTO;
import cn.chazzstudy.mapper.UserMapper;
import cn.chazzstudy.model.User;
import cn.chazzstudy.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 描述：访问主页时，判断登录状态
 *
 * @Email 2741953539@qq.com
 * @Author Chazz
 * @date 2020.03.08 18:10
 **/
@Controller
public class IndexController {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private QuestionService questionService;

    /**
     * 功能描述:响应首页访问请求,并返回相关数据
     *
     * @param request 获取请求的request
     * @return: java.lang.String-返回index页面路径
     * @Author: Chazz
     * @Date: 2020/3/13 11:55
     */
    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        //首先判断用户是否登录
        //获取页面的cookie值
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            //通过循环判断，是否存在名为"token"的cookie值
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    //如果存在token，则从数据库中查询出值token对应的user数据
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        //将查询到的user数据返回到页面
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        //拿到page，size值，进行数据查询
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
