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
 * 描述： TODO
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
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String dopublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model
    ){
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
                if (cookie.getName().equals("token")){
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
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.creat(question);
        return "redirect:/";
    }
}
