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
    /**
     *功能描述: 插入问题数据sql
     * @param question
     * @return: void
     * @Author: Chazz
     * @Date: 2020/3/27 10:28
     */
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void creat(Question question);

    /**
     *功能描述: 分页查询sql
     * @param offset
     * @param size
     * @return: java.util.List<cn.chazzstudy.model.Question>
     * @Author: Chazz
     * @Date: 2020/3/27 10:29
     */
    @Select("select * from question LIMIT #{offset},#{size}")
    List<Question> list(Integer offset, Integer size);

    /**
     *功能描述:查询数据库中的数据条数
     * @param
     * @return: java.lang.Integer
     * @Author: Chazz
     * @Date: 2020/3/27 10:30
     */
    @Select("select count(1) from question")
    Integer count();
}
