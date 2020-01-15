package life.majiang.community.cache;

import life.majiang.community.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

/*热门*/
@Component
@Data
public class HotTagCache {
    private List<String> hots=new ArrayList<>();

    /*TOPN  priorityQueue 优先队列 实现大顶堆小顶堆
    map只是结果   将hashmap中无序的取序  max取前三个*/
    public void updateTags(Map<String, Integer> tags) {
        int max = 12;
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>(max);

        //遍历map
        tags.forEach((name, priority) -> {
            //ctrl+alt v 抽取变量
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(priority);
            if (priorityQueue.size() < max) {
                //如果小于max，再往里面放值
                /*查看add源码，hottagdto需要复写comparable接口 */
                priorityQueue.add(hotTagDTO);
            } else {
                //取[0]
                HotTagDTO minHot = priorityQueue.peek();
                if (hotTagDTO.compareTo(minHot) > 0) {
                    // 比[0]大，则替换
                    priorityQueue.poll();
                    priorityQueue.add(hotTagDTO);
                }
            }
        });
        //取出 排的序
        List<String> sortedHots=new ArrayList<>();

        HotTagDTO poll = priorityQueue.poll();
        while (poll != null){
            sortedHots.add(0,poll.getName());
            poll = priorityQueue.poll();
        }

        hots = sortedHots;
        System.out.println(hots);
    }
}
