package life.majiang.community.controller;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.model.User;
import life.majiang.community.service.NotificationSevice;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/*个人详情页面
* profile page*/
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationSevice notificationSevice;

    //因为我需要放问题和回复者

    /*个人相关*/
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name="action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name="page",defaultValue = "1") Integer page,
                          @RequestParam(name="size",defaultValue = "5") Integer size){
        User user = null;
        user = (User) request.getSession().getAttribute("user");
        if(user == null || "".equals(user.getName())){
            model.addAttribute("error","Please login first.");
            return "redirect:/";
        }

        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            PaginationDTO pagination = questionService.findQuesByUserId(user.getId(), page, size);
            model.addAttribute("pagination",pagination);
        }else if("replies".equals(action)){
            //查询回复
            PaginationDTO pagination = notificationSevice.findRpliesByUserId(user.getId(), page, size);
            Long unreadCount = notificationSevice.findunreadCount(user.getId());
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","我的回复");
            model.addAttribute("unreadCount",unreadCount);
            model.addAttribute("pagination",pagination);
        }
        return "profile";
    }

    @PostMapping("/profile")
    public String doprofile(){
        return null;
    }
}
