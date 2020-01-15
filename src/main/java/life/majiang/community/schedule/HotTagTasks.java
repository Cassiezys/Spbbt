package life.majiang.community.schedule;

import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*定时任务
* 热门标签按顺序存储*/
@Component
@Slf4j
public class HotTagTasks {

    @Autowired
    private QuestionMapper questionMapper;

    @Scheduled(fixedRate = 10000)
//    @Scheduled(cron ="0 0 6,19 * * *" )
    public void hotTagSchedule() {
        int offset = 0;
        int limit = 20;

        log.info("hotTagSchedule start {}", new Date());
        List<Question> questionList = new ArrayList<>();
        while (offset != 0 || questionList.size() == limit) {
            // 不是第一页  还有下一页
            questionList = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, limit));
            offset += limit;
        }

        log.info("hotTagSchedule stop {}", new Date());
    }
}
