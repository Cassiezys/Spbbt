package life.majiang.community.dto;

import lombok.Data;

/*传输 问题的id
*       内容  问题类型 的dto 这样直接可以用requestBody 可以自动赋值
*                           而不需要requestParam一个一个传*/
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    //是评论1还是评论的评论  用枚举
    private Integer type;
}
