package life.majiang.community.service;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> dbUsers =  userMapper.selectByExample(userExample);
        if(dbUsers.size() == 0){
            //create this user
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            //update this user maybe modified or name 。。。ect
            User oldUser = dbUsers.get(0);
            User dbUser = new User();
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            /* 不为空的时候就更新 在usermapper.xml里面找到的使用方法*/
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(oldUser.getId());
            userMapper.updateByExampleSelective(dbUser, example);
        }
    }
}
