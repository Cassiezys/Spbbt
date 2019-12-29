package life.majiang.community.exception;

/*处理的是handler的异常*/
/*枚举所有的错误码，依据错误码来返回错误信息  问题列表*/
public enum CustomizeErrorCodeImp implements InCustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"该问题消失在银河.......  "),
    TARGET_PARAM_NOT_FOUND(2002,"没有选中问题或回复.......  "),
    NO_LOGIN(2003,"未登陆，请先登录  "),
    SYS_ERROR(2004,"程序员小哥开小差了,please try again later"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或者不存在"),
    COMMENT_NOT_FOUND(2006,"该评论已消失在银河......")
    ;

    private Integer code;
    private String message;

    CustomizeErrorCodeImp(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
