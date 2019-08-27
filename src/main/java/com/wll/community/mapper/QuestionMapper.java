package com.wll.community.mapper;

import com.wll.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("INSERT INTO question (title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tags) VALUES (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tags})")
    void insert(Question question);

    @Select("SELECT * from question limit #{offset},#{size}")
    List<Question> findList(@Param("offset") Integer offset,@Param("size") Integer size);

    @Select("SELECT COUNT(1) FROM question")
    Integer count();
}
