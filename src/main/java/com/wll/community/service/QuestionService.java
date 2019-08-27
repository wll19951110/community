package com.wll.community.service;

import com.wll.community.dto.PaginationDTO;
import com.wll.community.dto.QuestionDTO;
import com.wll.community.mapper.QuestionMapper;
import com.wll.community.mapper.UserMapper;
import com.wll.community.model.Question;
import com.wll.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO findList(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        //总数
        Integer count = questionMapper.count();
        paginationDTO.setPagination(count,page,size);

        if (page < 1) {
            page = 1;
        }

        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }

        //计算偏移量
        Integer offset = size * (page-1);
        List<Question> questionList = questionMapper.findList(offset,size);
        List<QuestionDTO> list = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            list.add(questionDTO);
            paginationDTO.setQuestions(list);
        }

        return paginationDTO;
    }
}
