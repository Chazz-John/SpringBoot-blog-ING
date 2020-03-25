package cn.chazzstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：github api的post请求数据对象
 * @Email 2741953539@qq.com
 * @Author Chazz
 * @date 2020.03.11 21:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
