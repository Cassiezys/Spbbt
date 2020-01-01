package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

/*需要问题和回复者*/
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    //可以是评论也可以是问题
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;
}
