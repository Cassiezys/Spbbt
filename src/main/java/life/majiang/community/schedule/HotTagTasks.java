package life.majiang.community.schedule;

import life.majiang.community.cache.HotTagCache;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/*定时任务
 * 热门标签按顺序存储
 * 优先级priority = */
@Component
@Slf4j
public class HotTagTasks {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate = 1000 * 60 * 60 *3)
//    @Scheduled(cron ="0 0 6,19 * * *" )
    public void hotTagSchedule() {
        int offset = 0;
        int limit = 20;

        log.info("hotTagSchedule start {}", new Date());
        List<Question> questionList = new ArrayList<>();
        Map<String, Integer> priorities = new HashMap<>();
        while (offset == 0 || questionList.size() == limit) {
            // 不是第一页  还有下一页
            questionList = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, limit));
            /*将问题取出，并且取出其中的标签并且计算权重*/
            for (Question question : questionList) {
                String[] tags = StringUtils.split(question.getTag(), ",");
                for (String tag : tags) {
                    Integer priority = priorities.get(tag);
                    if (priority != null) {
                        priorities.put(tag, priority + 5 + question.getCommentCount());
                    } else {
                        priorities.put(tag,  5 + question.getCommentCount());
                    }
                }
            }
            offset += limit;
        }
        /*打印一下
        priorities.forEach(
                (k,v)->{
                    System.out.print(k);
                    System.out.print(":");
                    System.out.print(v);
                    System.out.println();
                }
        );*/

        hotTagCache.updateTags(priorities);
        log.info("hotTagSchedule stop {}", new Date());
    }
}
