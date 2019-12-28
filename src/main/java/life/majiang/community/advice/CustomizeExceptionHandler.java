package life.majiang.community.advice;

import life.majiang.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {

    /*异常返回error页面  不需要返回json所悟不用responsebody*/
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model) {
        if(ex instanceof CustomizeException){
            //如果是我自己定义的Exception
            model.addAttribute("message",ex.getMessage());
        }else{
            model.addAttribute("message","程序员小哥开小差了,please try again later");
        }
        return new ModelAndView( "error");
    }
}
