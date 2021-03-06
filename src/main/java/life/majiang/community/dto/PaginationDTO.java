package life.majiang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/*
 * 分页所承载的元素
 * page segment shown*/
@Data
public class PaginationDTO<T> {
    private List<T> dataList;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer totalPage;
    private Integer page;
    private List<Integer> pages= new ArrayList<>();;

    public void setPagination(Integer tatalquescount, Integer page, Integer size) {

        pages.add(page);
        for(int i=1;i<=3;++i){
            if(page-i>0){
                pages.add(0,page-i);
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }

        //是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        //是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        //last page
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
