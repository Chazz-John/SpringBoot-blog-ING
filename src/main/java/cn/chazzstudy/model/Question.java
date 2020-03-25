package cn.chazzstudy.model;

import lombok.Data;

/**
 * 描述： TODO
 *
 * @Author Chazz
 * @date 2020/3/25 15:27
 * @Email 2741953539@qq.com
 **/
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
