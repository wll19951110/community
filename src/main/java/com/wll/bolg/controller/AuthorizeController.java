package com.wll.bolg.controller;


import com.wll.bolg.dto.AccessTokenDTO;
import com.wll.bolg.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,@RequestParam(name = "state")String state ){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("b89cbbd2acdcdbb4511e");
        accessTokenDTO.setClient_secret("6a9fe89427f3047a7f6148f63653ea4c85e0b298");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        githubProvider.getAccessToken(accessTokenDTO);
        return "index";
    }


}
