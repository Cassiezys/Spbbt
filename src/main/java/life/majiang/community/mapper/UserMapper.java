package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/*
* response to Javabeen User dto*/
@Mapper
public interface UserMapper {


    @Insert("INSERT INTO USER (account_id,name,token,gmt_create,gmt_modified) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
/*
* #{ 会把find（a） a放进去；如果不是类的话 要加@Param（“token”）}*/
    @Select("select * from user where token = #{token}")
    User findToken(@Param("token") String token);

}
