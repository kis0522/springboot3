package com.example.mobile.controller;

import com.example.mobile.dto.MobileFormDto;
import com.example.mobile.entity.Mobile;
import com.example.mobile.service.MobileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/mobile")
@Controller
@RequiredArgsConstructor
public class controller {
    private final MobileService mobileService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String main(){
        return "main";
    }
    @GetMapping("/onboarding")
    public String onboarding(){
        return "onboarding";
    }

    @GetMapping("/login_page")
    public String loginMobile(){
        return "login_page";
    }

    @GetMapping("/login_page/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요.");
        return "login_page";
    }

    @GetMapping("/new")
    public String mobileForm(Model model){
        model.addAttribute("mobileFormDto",new MobileFormDto());
        return "join_page";
    }
    @PostMapping("/new")
    public String newMobile(@Valid MobileFormDto mobileFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "join_page";
        }
        try{
            Mobile mobile = Mobile.createMobile(mobileFormDto, passwordEncoder);
            mobileService.saveMobile(mobile);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "join_page";
        }
        return "redirect:/login_page";
    }



}
