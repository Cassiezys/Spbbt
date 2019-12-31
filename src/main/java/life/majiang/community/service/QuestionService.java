package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.exception.CustomizeErrorCodeImp;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.mapper.QuestionExtMapper;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.QuestionExample;
import life.majiang.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*for page segment achieve */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

/*返回分页
*   show segment pages firstPage*/
    public PaginationDTO allQuestion(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer tatalquescount = (int) questionMapper.countByExample(new QuestionExample());
        if (tatalquescount % size == 0) {
            paginationDTO.setTotalPage(tatalquescount / size);
        } else {
            paginationDTO.setTotalPage(tatalquescount / size + 1);
        }
        if(page<1){
            page = 1;
        }else if(page>paginationDTO.getTotalPage()){
            page =paginationDTO.getTotalPage();
        }
        paginationDTO.setPage(page);
        paginationDTO.setPagination(tatalquescount, page,size);
        // 5*9(page-1)
        Integer offset = size *(page-1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question:questions){
           User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO= new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionlist(questionDTOList);
        return paginationDTO;
    }

    /*show segment pages by userId*/
    public PaginationDTO findQuesById(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer tatalquescount = (int) questionMapper.countByExample(questionExample);
        if (tatalquescount % size == 0) {
            paginationDTO.setTotalPage(tatalquescount / size);
        } else {
            paginationDTO.setTotalPage(tatalquescount / size + 1);
        }
        if(page<1){
            page = 1;
        }else if(page>paginationDTO.getTotalPage()){
            page =paginationDTO.getTotalPage();
        }
        paginationDTO.setPage(page);
        paginationDTO.setPagination(tatalquescount, page,size);
        // 5*9(page-1)
        Integer offset = size *(page-1);

        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question:questions){
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO= new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionlist(questionDTOList);
        return paginationDTO;
    }

    /* details about this Question*/
    public QuestionDTO getQuesById(Long quesid) {
        Question question = questionMapper.selectByPrimaryKey(quesid);
        if(question == null){
            throw new CustomizeException(CustomizeErrorCodeImp.QUESTION_NOT_FOUND);
        }
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDTO questionDTO= new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    /*判断增加新问题还是修改我的问题*/
    public void createOrUpdate(Question question) {
        if(question.getId()== null){
            //new question
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        }else{
            //modify my question
            Question updateQues = new Question();
            updateQues.setGmtModified(System.currentTimeMillis());
            updateQues.setTitle(question.getTitle());
            updateQues.setDescription(question.getDescription());
            updateQues.setTag(question.getTag());
            updateQues.setId(question.getId());
            //应该用account——id而不是id

            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQues, questionExample);
            if(updated !=1){
                throw new CustomizeException(CustomizeErrorCodeImp.QUESTION_NOT_FOUND);
            }
        }
    }

    /*增加问题的阅读数*/
    public void incView(Long quesid) {
        /*防止多线程并发导致阅读数错误*/
        Question question = new Question();
        question.setId(quesid);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO questionDTO) {
        if(StringUtils.isBlank(questionDTO.getTag())){
            return new ArrayList<>();
        }
        String regexpTag = StringUtils.replace(questionDTO.getTag(), ",", "|");
        System.out.println(regexpTag);
        /*拼接字符串  先split
        *  String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));*/
        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setTag(regexpTag);
        List<Question> questionTagList = questionExtMapper.selectRelated(question);
        List<QuestionDTO> questionDTOList = questionTagList.stream().map(ques -> {
            QuestionDTO questionDTO1 = new QuestionDTO();
            BeanUtils.copyProperties(ques,questionDTO1);
            return questionDTO1;
        }).collect(Collectors.toList());
        return questionDTOList;
    }
}
