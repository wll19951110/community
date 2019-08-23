package com.wll.bolg.dto;

import com.wll.bolg.model.User;
import lombok.Data;

@Data
public class QuestionDTO {

    private String id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tags;
    private User user;


}
