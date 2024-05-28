package com.example.mobile.controller;

import com.example.mobile.service.MobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MobileService service;

    @GetMapping(value="/")
    public String main(Model model, @AuthenticationPrincipal User user){
        if(user != null){
            model.addAttribute("name",service.getMobile(user.getUsername()).getName());
        }
        return "main";
    }

    @GetMapping("/onboarding")
    public String onboarding(){
        return "onboarding";
    }

    @GetMapping("/err")
    public String errorPage(){
        return "redirect:/";
    }
}
