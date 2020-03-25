package cn.chazzstudy.model;

import lombok.Data;

/**
 * @Description
 * @Email 2741953539@qq.com
 * @Author Chazz
 * @date 2020.03.12 15:28
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private long gtmCreate;
    private long gtmModified;
    private String avatarUrl;
}
