package life.majiang.community.exception;

public interface InCustomizeErrorCode {
    String getMessage();
    //如何将内层的信息返回给外层 利用传code码 将service的信息传回给controller
    Integer getCode();
}
