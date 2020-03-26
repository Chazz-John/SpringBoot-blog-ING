package cn.chazzstudy.mapper;

import cn.chazzstudy.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 描述： Question数据库查询接口
 *
 * @Author Chazz
 * @date 2020/3/25 15:21
 * @Email 2741953539@qq.com
 **/
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void creat(Question question);
    @Select("select * from question")
    List<Question> list();
}
