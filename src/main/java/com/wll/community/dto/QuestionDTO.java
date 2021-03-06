package com.wll.community.dto;

import com.wll.community.model.User;
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
