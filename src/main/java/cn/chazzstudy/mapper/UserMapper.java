package cn.chazzstudy.mapper;

import cn.chazzstudy.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 描述：数据库操作mapper
 * @Email 2741953539@qq.com
 * @Author Chazz 
 * @date 2020.03.12 14:45
 */
@Mapper
public interface UserMapper {
    /**
     *功能描述:将封装User实例插入到数据库中
     * @param user model中的User实例
     * @return: void
     * @Author: Chazz
     * @Date: 2020/3/13 11:39
     */
    @Insert("insert into user (name,token,account_id,gtm_create,gtm_modified,avatar) values (#{name},#{token},#{accountId},#{gtmCreate},#{gtmModified},#{avatar})")
    void insertUser(User user);

    /**
     *功能描述: 通过token值查询User数据
     * @param token 发送到页面的cooky值，通过UUID生成的token，并非api返回的token
     * @return: User 用户数据对象
     * @Author: Chazz
     * @Date: 2020/3/13 0:51
     */
    @Select("select * from user where token = #{token}")
    User findByToken(String token);
    @Select("select * from user where id = #{id}")
    User findById(Integer id);
}
