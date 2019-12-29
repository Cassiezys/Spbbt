package life.majiang.community.dto;

import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import lombok.Data;
import sun.util.resources.ga.LocaleNames_ga;

@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer LikeCount;
  //  private Question question;
    private User user;
}
