package cn.chazzstudy.controller;

import cn.chazzstudy.controller.dto.AccessTokenDTO;
import cn.chazzstudy.controller.dto.GithubUser;
import cn.chazzstudy.controller.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Email 2741953539@qq.com
 * @Author Chazz
 * @date 2020.03.11 21:03
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.uri}")
    private String clientUri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        String accessToken = githubProvider.getAccessToken(new AccessTokenDTO(clientId, clientSecret, code,clientUri, state));
        GithubUser user = githubProvider.getUser(accessToken);
        return "index";
    }
}
