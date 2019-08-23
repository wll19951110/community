package com.wll.bolg.controller;

import com.wll.bolg.mapper.QuestionMapper;
import com.wll.bolg.mapper.UserMapper;
import com.wll.bolg.model.Question;
import com.wll.bolg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title")String title,
                            @RequestParam("description")String description,
                            @RequestParam("tags")String tags,
                            HttpServletRequest request,
                            Model model){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tags",tags);

        if (title==null || title.equals("")){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }

        if (description==null || description.equals("")){
            model.addAttribute("error","问题描述不能为空");
            return "publish";
        }

        if (tags==null || tags.equals("")){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies.length!=0 && cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }

            }
        }

        if (user==null){
            model.addAttribute("error","用户未登录");
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTags(tags);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.insert(question);

        return "redirect:/";

    }
}
