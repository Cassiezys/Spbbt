package life.majiang.community.dto;

import life.majiang.community.exception.CustomizeErrorCodeImp;
import life.majiang.community.exception.CustomizeException;
import lombok.Data;

import java.util.List;

/*返回到页面的结果码所对应的message*/
@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    //泛型，可以传user 也可以传list啥都行
    private T data;

    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCodeImp errorCodeImp) {
        return errorOf(errorCodeImp.getCode(),errorCodeImp.getMessage());
    }

    public static ResultDTO errorOf(CustomizeException ex){
        return errorOf(ex.getCode(), ex.getMessage());
    }
    public static ResultDTO successOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(222);
        resultDTO.setMessage("任务已完成，正在回归地球");
        return resultDTO;
    }
    public static <T> ResultDTO successOf(T t){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(222);
        resultDTO.setMessage("任务已完成，正在回归地球");
        resultDTO.setData(t);
        return resultDTO;
    }

}
