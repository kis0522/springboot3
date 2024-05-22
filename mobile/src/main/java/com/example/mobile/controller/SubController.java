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

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "mobile")
public class SubController {
    private final MobileService service;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginMobile(){
        return "login/login_page";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요.");
        return "login/login_page";
    }

    @GetMapping("/new")
    public String mobileForm(Model model){
        model.addAttribute("mobileFormDto",new MobileFormDto());
        return "login/join_page";
    }
    @PostMapping("/new")
    public String newMobile(@Valid MobileFormDto mobileFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "login/join_page";
        }
        try{
            Mobile mobile = Mobile.createMobile(mobileFormDto, passwordEncoder);
            service.saveMobile(mobile);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "login/join_page";
        }
        return "redirect:/mobile/login";
    }

    @GetMapping("/search")
    public String search_page(){
        return "search/search_page";
    }

    @GetMapping("/product_detail_page")
    public String product_detail_page(){
        return "search/product_detail_page";
    }

    @GetMapping("/search_info_recipe_page")
    public String search_info_recipe_page(){
        return "search/search_info_recipe_page";
    }

    @GetMapping("/search_map_page")
    public String search_map_page(){
        return "search/search_map_page";
    }

    @GetMapping("/recipe")
    public String recipe_page(){
        return "recipe/recipe_page";
    }

    @GetMapping("/recipe2_1")
    public String recipe2_1(){
        return "recipe/recipe2_1";
    }

    @GetMapping("/recipe2_2")
    public String recipe2_2(){
        return "recipe/recipe2_2";
    }

    @GetMapping("/recipe3")
    public String recipe3(){
        return "recipe/recipe3";
    }
}
