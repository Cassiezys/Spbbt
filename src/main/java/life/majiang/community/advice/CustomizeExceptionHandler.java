package life.majiang.community.advice;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCodeImp;
import life.majiang.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {

    /*异常返回error页面  不需要返回json所悟不用responsebody    含有json可以用printwriter暴力写回去*/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ModelAndView handle(Throwable ex, Model model,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            //如果获取的是json数据结构  要定义成ResponseBody才返回json要强转
            ResultDTO resultDTO;
            if(ex instanceof CustomizeException){
                //如果是我自己定义的Exception
                resultDTO= ResultDTO.errorOf((CustomizeException) ex);
            }else{
                resultDTO= ResultDTO.errorOf(CustomizeErrorCodeImp.SYS_ERROR);
            }
            try {
                /*将object转化为json对象*/
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter printWriter = response.getWriter();
                printWriter.write(JSON.toJSONString(resultDTO));
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            //错误页面跳转  要返回modelandview 所以json要用强转
            if(ex instanceof CustomizeException){
                //如果是我自己定义的Exception
                model.addAttribute("message",ex.getMessage());
            }else{
                model.addAttribute("message",CustomizeErrorCodeImp.SYS_ERROR.getMessage());
            }
            return new ModelAndView( "error");
        }
    }
}
