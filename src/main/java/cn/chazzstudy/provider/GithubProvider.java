package cn.chazzstudy.provider;

import cn.chazzstudy.dto.AccessTokenDTO;
import cn.chazzstudy.dto.GithubUser;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 描述：与githubapi进行数据交换
 *
 * @author Chazz
 * @date 2020/3/13 0:29
 * @Email 2741953539@qq.com
 **/
@Component
public class GithubProvider {
    /**
     *功能描述:通过okhttp3 向github api发送post请求获取token值
     * @param accessTokenDTO 向github api请求token的数据对象
     * @return: java.lang.String token-github api返回的token值
     * @Author: Chazz
     * @Date: 2020/3/13 0:49
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            //将完整的token码裁剪出来
            String s = response.body().string();
            String token = s.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *功能描述:通过okhttp3向api发送get获取user数据对象
     * @param accessToken api的token值
     * @return: cn.chazzstudy.dto.GithubUser-api返回的user数据对象
     * @Author: Chazz
     * @Date: 2020/3/13 0:50
     */
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                //通过token码获取github用户信息
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String s =  response.body().string();
            GithubUser githubUser = JSON.parseObject(s, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
