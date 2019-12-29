package life.majiang.community.dto;

import lombok.Data;

/*传输 问题的id   从数据库中获取的
*       内容  问题类型 的dto 这样直接可以用requestBody 可以自动赋值
*                           而不需要requestParam一个一个传*/
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    //是评论1还是评论的评论  用枚举
    private Integer type;
    //上面是关于是评论时传递而来的  区分传过来的还有返回给页面的，因为会评论
}
