package life.majiang.community.exception;

/*自定义异常 从其他要抛异常的地方设置它
* 最后在异常处理的advice中输出*/
public class CustomizeException extends RuntimeException {
    private Integer code;
    private String message;

    public CustomizeException(InCustomizeErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
