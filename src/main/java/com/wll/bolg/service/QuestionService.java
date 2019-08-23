package com.wll.bolg.service;

import com.wll.bolg.dto.QuestionDTO;
import com.wll.bolg.mapper.QuestionMapper;
import com.wll.bolg.mapper.UserMapper;
import com.wll.bolg.model.Question;
import com.wll.bolg.model.User;
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

    public List<QuestionDTO> findList() {
        List<Question> questionList = questionMapper.findList();
        List<QuestionDTO> list = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            list.add(questionDTO);
        }

        return list;
    }
}
