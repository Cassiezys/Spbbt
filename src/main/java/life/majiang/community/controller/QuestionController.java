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
    public String question(@PathVariable(name="quesid") Integer quesid,
                           Model model){
        QuestionDTO questionDTO = questionService.getQuesById(quesid);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
