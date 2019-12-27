package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.QuestionExample;
import life.majiang.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*for page segment achieve */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
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
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
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
    public PaginationDTO findQuesById(int userId, Integer page, Integer size) {
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

    /* show detailed this user's Question*/
    public QuestionDTO getQuesById(Integer quesid) {
        Question question = questionMapper.selectByPrimaryKey(quesid);
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
            questionMapper.updateByExampleSelective(updateQues, questionExample);
        }
    }
}
