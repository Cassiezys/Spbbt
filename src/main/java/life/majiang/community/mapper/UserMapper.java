package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

/*
* response to Javabeen User dto*/
@Mapper
public interface UserMapper {


    @Insert("INSERT INTO USER (account_id,name,token,gmt_create,gmt_modified,avatar_url) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);
/*
* #{ 会把find（a） a放进去；如果不是类的话 要加@Param（“token”）}*/
    @Select("select * from user where token = #{token}")
    User findToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id = #{accountId}")
    User findUserByAccountId(@Param("accountId") String accountId);

    @Update("update user set name=#{name}, token=#{token}, gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where account_id = #{accountId}")
    void update(User user);
}
