package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
/*返回分页
*   show segment pages*/
    public PaginationDTO allQuestion(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer tatalquescount = questionMapper.countquestion();
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

        List<Question> questions = questionMapper.allQuestion(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question:questions){
           User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO= new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionlist(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO findQuesById(int userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer tatalquescount = questionMapper.countquestionById(userId);
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

        List<Question> questions = questionMapper.findQuesById(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question:questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO= new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionlist(questionDTOList);
        return paginationDTO;
    }
}
