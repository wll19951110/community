package com.wll.bolg.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer count, Integer page, Integer size) {

        if (count % size == 0){
            totalPage = count / size;
        }else {
            totalPage = count / size+1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        this.page = page;
        //判断页码数
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //判断上一页按钮
        if (page==1){
            showPrevious = false;
        }else {
            showPrevious = true;
        }

        //判断下一页按钮
        if (page.equals(totalPage)){
            showNext = false;
        }else {
            showNext = true;
        }

        //判断前往第一页按钮
        if (pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }

        //判断前往最后一页按钮
        if (pages.contains(totalPage)){
            showEndPage = false;
        }else{
            showEndPage = true;
        }
    }
}
