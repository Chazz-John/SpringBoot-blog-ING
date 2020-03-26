package cn.chazzstudy.controller;

import cn.chazzstudy.mapper.QuestionMapper;
import cn.chazzstudy.mapper.UserMapper;
import cn.chazzstudy.model.Question;
import cn.chazzstudy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 描述： 处理发布页面的相关请求
 *
 * @Author Chazz
 * @date 2020/3/25 14:16
 * @Email 2741953539@qq.com
 **/
@Controller
public class PublishController {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private QuestionMapper questionMapper;
    /**
     *功能描述:响应publish请求
     * @param
     * @return: java.lang.String——响应到publish页面
     * @Author: Chazz
     * @Date: 2020/3/26 20:15
     */
    @GetMapping("/publish")
    public String publish(){

        return "publish";
    }

    /**
     *功能描述:提交发布的问题到数据库
     * @param title——问题标题
     * @param description——问题内容
     * @param tag——问题标签
     * @param request——页面缓存
     * @param model
     * @return: java.lang.String——响应路径
     * @Author: Chazz
     * @Date: 2020/3/26 20:05
     */
    @PostMapping("/publish")
    public String dopublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model
    ){
        //对发布的内容进行判空
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null || title == ""){
            model.addAttribute("error", "标题不能为空！");
            return "publish";
        }
        if (description == null || description == ""){
            model.addAttribute("error", "问题不能为空！");
            return "publish";
        }
        if (description == null || tag == ""){
            model.addAttribute("error", "标签不能为空！");
            return "publish";
        }

        User user = null;
        //获取页面的cookie值
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            //通过循环判断，是否存在名为"token"的cookie值
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())){
                    String token = cookie.getValue();
                    //如果存在token，则从数据库中查询出值token对应的user数据
                    user = userMapper.findByToken(token);
                    if (user != null){
                        //将查询到的user数据返回到页面
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
            if (user == null){
                model.addAttribute("error", "用户未登录！");
                return "publish";
            }
        }//在发布问题之前判断用户是否登陆

        //创建一个Question对象，存储发布问题的内容
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        //将Question对象插入数据库
        questionMapper.creat(question);
        return "redirect:/";
    }
}
