package life.majiang.community.controller;

import life.majiang.community.dto.CommentDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.enums.CommentTypeEnum;
import life.majiang.community.service.CommentService;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/* questionDetail*/
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    //需要拿comment列表
    @Autowired
    private CommentService commentService;

    /*某问题详情 question detail*/
    @GetMapping("/question/{quesid}")
    public String question(@PathVariable(name="quesid") Long quesid,
                           Model model){
        QuestionDTO questionDTO = questionService.getQuesById(quesid);

        //获取所有评论（各评论中也有user的概念）
        List<CommentDTO> commentList = commentService.findCommentListByIdTaget(quesid, CommentTypeEnum.QUESTION);
        //获取成功之后累加阅读数
        questionService.incView(quesid);
        model.addAttribute("question",questionDTO);
        model.addAttribute("commentList",commentList);
        return "question";
    }

}
