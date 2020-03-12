package cn.chazzstudy.mapper;

import cn.chazzstudy.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description
 * @Email 2741953539@qq.com
 * @Author Chazz
 * @date 2020.03.12 14:45
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,token,account_id,gtm_create,gtm_modified) values (#{name},#{token},#{accountId},#{gtmCreate},#{gtmModified})")
    void insertUser(User user);
    @Select("select * from user where token = #{token}")
    User findByToken(String token);
}
