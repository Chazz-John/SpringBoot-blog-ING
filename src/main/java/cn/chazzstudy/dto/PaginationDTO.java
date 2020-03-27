package cn.chazzstudy.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：分页页面数据模型
 *
 * @Author Chazz
 * @date 2020/3/27 9:55
 * @Email 2741953539@qq.com
 **/
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showBefore;
    private boolean showNext;
    private boolean showFist;
    private boolean showEnd;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;
    /**
     *功能描述: 对PaginationDTO对象中的所有数据进行处理
     * @param totalCount 数据库中问题总数
     * @param page 前端当前需要显示的页号
     * @param size 每页的数据条数
     * @return: void
     * @Author: Chazz
     * @Date: 2020/3/27 13:55
     */
    public void setPagination(Integer totalCount, Integer page, Integer size) {

        //计算最大页数
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        //对前端page数据进行容错性处理
        if (page<1){
            page = 1;
        }
        if (page>=totalPage){
            page = totalPage;
        }
        //将前端的page值存入PaginationDTO中，进行回传
        this.page = page;

        //添加要显示的页码列
        pages.add(page);
        for (Integer i = 1;i<=3;i++){
            //添加前三个页码
            if (page-i>0){
                pages.add(0, page-i);
            }
            //添加后三个页码
            if (page+i<=totalPage){
                pages.add(page+i);
            }
        }

        //是否显示前一页标签
        if (page == 1) {
            showBefore = false;
        } else {
            showBefore = true;
        }

        //是否显示后一页标签
        if (page.equals(totalCount)) {
            showNext = false;
        } else {
            showNext = true;
        }

        //判断是否显示第一页标签
        if (pages.contains(1)) {
            showFist = false;
        } else {
            showFist = true;
        }
        //判断是否显示最后一页标签
        if (pages.contains(totalPage)) {
            showEnd = false;
        } else {
            showEnd = true;
        }
    }
}
