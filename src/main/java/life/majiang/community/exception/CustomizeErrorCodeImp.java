package life.majiang.community.exception;

/*处理的是handler的异常*/
/*枚举所有的错误码，依据错误码来返回错误信息*/
public enum CustomizeErrorCodeImp implements InCustomizeErrorCode {
    QUESTION_MOT_FOUND("该问题消失在银河.......  ");

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCodeImp(String message) {
        this.message = message;
    }
}
