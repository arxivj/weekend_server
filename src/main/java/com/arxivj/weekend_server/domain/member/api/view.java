package com.arxivj.weekend_server.domain.member.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class view {

    @GetMapping("/members/signin")
    public String signIn(){
        return "/member/signin";
    }

    @GetMapping("members/signup")
    public String signUp(){
        return "/member/signup";
    }

}
