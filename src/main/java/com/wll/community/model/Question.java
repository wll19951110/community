package com.wll.community.model;

import lombok.Data;

@Data
public class Question {

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


}
