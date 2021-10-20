package com.jamesdev.blog.controller;

import com.jamesdev.blog.config.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/auth/join")
    public String join(){
        return "user/join";
    }
    @GetMapping("/auth/login")
    public String login(){
        return "/user/login";
    }
    @GetMapping("/user/edit")
    public String editUser(@AuthenticationPrincipal PrincipalDetails principalDetails , Model model){
        model.addAttribute("principal",principalDetails);
        return "user/editUser";
    }
}
