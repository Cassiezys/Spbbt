package life.majiang.community.service;

import life.majiang.community.dto.CommentDTO;
import life.majiang.community.enums.CommentTypeEnum;
import life.majiang.community.exception.CustomizeErrorCodeImp;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.mapper.CommentMapper;
import life.majiang.community.mapper.QuestionExtMapper;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insert(Comment comment) {
        if(comment.getParentId()==null||comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCodeImp.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCodeImp.TYPE_PARAM_WRONG);
        }
        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null){
                throw new CustomizeException(CustomizeErrorCodeImp.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else{
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null){
                throw  new CustomizeException(CustomizeErrorCodeImp.QUESTION_NOT_FOUND);
            }
            /*添加评论和增加评论数  -- 事务 */
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }
    /*返回该问题的评论*/
    public List<CommentDTO> getCommentById(Long quesid) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(quesid).andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        //确保是问题的评论
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        /*如果一个问题下的很多个评论都是同一个用户，那么每次查都是重复工作，所以
        for (Comment comment : commentList) {
            User user = userMapper.selectByPrimaryKey(comment.getCommentator());
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(user);
            commentDTOList.add(commentDTO);
        }*/

        //防止查同一个用户很多次
        if(commentList.size() == 0){
            return new ArrayList<>();
        }
        //有几个不一样的用户就找几个 减少查数据库的次数(用set）在转为list
        Set<Long> commentatorSet = commentList.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> commentatorList = new ArrayList<>();
        commentatorList.addAll(commentatorSet);

        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(commentatorList);
        List<User> userList = userMapper.selectByExample(userExample);

        //匹配评论和用户
      /*  for (Comment comment : commentList) {
            for (User user : userList) {

            }
        }  1.暴力破解*/
        //2.用MAP  将user变成map；
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        List<CommentDTO> commentDTOList = commentList.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(commentDTO.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOList;
    }
}
