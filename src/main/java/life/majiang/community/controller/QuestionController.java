package life.majiang.community.controller;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/* questionDetail*/
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{quesid}")
    public String question(@PathVariable(name="quesid") Long quesid,
                           Model model){
        QuestionDTO questionDTO = questionService.getQuesById(quesid);
        //获取成功之后累加阅读数
        questionService.incView(quesid);
        model.addAttribute("question",questionDTO);
        return "question";
    }

}
