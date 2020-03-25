package cn.chazzstudy.controller;

import cn.chazzstudy.dto.AccessTokenDTO;
import cn.chazzstudy.dto.GithubUser;
import cn.chazzstudy.mapper.UserMapper;
import cn.chazzstudy.model.User;
import cn.chazzstudy.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 描述:响应github api的回调请求，并向页面设置自定义cooky值
 *
 * @author Chazz
 * @date 2020/3/13 0:29
 * @Email 2741953539@qq.com
 **/
@Controller
public class AuthorizeController {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired
    private GithubProvider githubProvider;
    /**
     * 通过application.yaml文件设值
     */
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.uri}")
    private String redirectUri;

    /**
     * 功能描述:响应github api的回调请求，并向页面设置自定义cooky值
     *
     * @param code     github api 返回的code
     * @param state    页面设置的state值
     * @param response 设置cooky值
     * @return: java.lang.String-返回路径
     * @Author: Chazz
     * @Date: 2020/3/13 11:10
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        //通过githubProvider.getAccessToken方法获取github api的token值
        String accessToken = githubProvider.getAccessToken(
                new AccessTokenDTO(clientId,
                        clientSecret,
                        code,
                        redirectUri,
                        state));
        //通过githubProvider.getUser获取githubUser数据对象
        GithubUser githubUser = githubProvider.getUser(accessToken);
        //将获取到的githubUser的信息部署到我们自定义的User实例中
        if (githubUser != null && githubUser.getId() != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGtmCreate(System.currentTimeMillis());
            user.setGtmModified(user.getGtmCreate());
            user.setAvatarUrl(githubUser.getAvatar_url());
            //将user封装完成后，将数据写入数据库
            userMapper.insertUser(user);
            //通过response向页面写入cookie，用于实现持久化登录
            response.addCookie(new Cookie("token", token));
            //不管成功与否都重定向会首页
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
