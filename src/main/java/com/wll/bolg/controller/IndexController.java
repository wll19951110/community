package com.wll.bolg.controller;


import com.wll.bolg.dto.PaginationDTO;
import com.wll.bolg.dto.QuestionDTO;
import com.wll.bolg.mapper.UserMapper;
import com.wll.bolg.model.User;
import com.wll.bolg.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size){


        PaginationDTO paginationDTO = questionService.findList(page,size);
        model.addAttribute("pagination",paginationDTO);
        return "index";
    }

}
