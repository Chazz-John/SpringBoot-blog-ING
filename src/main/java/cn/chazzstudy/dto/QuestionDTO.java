package cn.chazzstudy.dto;

import cn.chazzstudy.model.User;
import lombok.Data;

/**
 * 描述： question传输层对象
 *
 * @Author Chazz
 * @date 2020/3/26 14:46
 * @Email 2741953539@qq.com
 **/
@Data
public class QuestionDTO {
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
    private User user;
}
