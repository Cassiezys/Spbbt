package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/*
* response to Javabeen User dto*/
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USER (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified}")
    void insert(User user);
}
