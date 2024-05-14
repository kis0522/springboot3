package com.example.mobile.controller;

import com.example.mobile.dto.MobileFormDto;
import com.example.mobile.entity.Mobile;
import com.example.mobile.service.MobileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class controller {
    private final MobileService mobileService;
    /*private final PasswordEncoder passwordEncoder;*/
    @GetMapping("/")
    public String index(Model model){
        return "main";
    }

    @GetMapping("/onboarding")
    public String onboarding(Model model){
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

    @GetMapping("/join_page")
    public String mobileForm(Model model){
        model.addAttribute("mobileFormDto",new MobileFormDto());
        return "join_page";
    }
    @PostMapping("/join_page")
    public String newMobile(@Valid MobileFormDto mobileFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "join_page";
        }
        try{
            Mobile mobile = Mobile.createMobile(mobileFormDto);
            mobileService.saveMobile(mobile);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "join_page";
        }
        return "redirect:/";
    }

    @GetMapping("/my_page")
    public String my_page(){
        return "my_page";
    }

    @GetMapping("/my_pick_page")
    public String my_pick_page(){
        return "my_pick_page";
    }

    @GetMapping("/product_detail_page")
    public String product_detail_page(){
        return "product_detail_page";
    }

    @GetMapping("/recipe2_1")
    public String recipe2_1(){
        return "recipe2_1";
    }

    @GetMapping("/recipe2_2")
    public String recipe2_2(){
        return "recipe2_2";
    }

    @GetMapping("/recipe3")
    public String recipe3(){
        return "recipe3";
    }

    @GetMapping("/recipe_page")
    public String recipe_page(){
        return "recipe_page";
    }

    @GetMapping("/recipe_write_page")
    public String recipe_write_page(){
        return "recipe_write_page";
    }

    @GetMapping("/search_info_recipe_page")
    public String search_info_recipe_page(){
        return "search_info_recipe_page";
    }

    @GetMapping("/search_map_page")
    public String search_map_page(){
        return "search_map_page";
    }

    @GetMapping("/search_page")
    public String search_page(){
        return "search_page";
    }

}
