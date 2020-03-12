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
 * @Description
 * @Email 2741953539@qq.com
 * @Author Chazz
 * @date 2020.03.11 21:03
 */
@Controller
public class AuthorizeController {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.uri}")
    private String redirectUri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        String accessToken = githubProvider.getAccessToken(new AccessTokenDTO(clientId, clientSecret, code,redirectUri, state));
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGtmCreate(System.currentTimeMillis());
            user.setGtmModified(user.getGtmCreate());
            userMapper.insertUser(user);
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        }else {
            return "redirect:/";
        }
    }
}
