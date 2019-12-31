package life.majiang.community.controller;

import life.majiang.community.cache.TagCache;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/*问题相关
* publish question page*/
@Controller
public class PublishController {

    @Autowired
    QuestionService questionService;

    /*修改问题*/
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name="id") Long id,
                       Model model){
        QuestionDTO question = questionService.getQuesById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    /*第一次打开*/
    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    /*新建/修改问题*/
    @PostMapping("/publish")
    public String dopublish(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "tag",required = false) String tag,
                            @RequestParam(value = "id",required = false) Long id,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("tags", TagCache.get());

        if(title ==null||"".equals(title)){
            model.addAttribute("error","有标题能更快解决噢~");
            return "publish";
        }

        if(description == null ||"".equals(description)){
            model.addAttribute("error","有内容更有看点");
            return "publish";
        }
        if(tag == null ||"".equals(tag)){
            model.addAttribute("error","有标签，才能为自己代言");
            return "publish";
        }
        User user = null;
        user = (User) request.getSession().getAttribute("user");
      //  System.out.println(user.getName());
        if(user == null || "".equals(user.getName())){
            model.addAttribute("error","no this user,please register first");
            return "publish";
        }

        String fileInvalid = TagCache.fileInvalid(tag);
        if(StringUtils.isNotBlank(fileInvalid)){
            model.addAttribute("error",fileInvalid+"无法为问题代言");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setId(id);
        //应该用account——id而不是id
        question.setCreator(user.getId());
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
