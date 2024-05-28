package com.example.mobile.controller;

import com.example.mobile.dto.MobileFormDto;
import com.example.mobile.entity.Mobile;
import com.example.mobile.service.MobileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "user")
public class UserController {
    private final MobileService service;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/my_page")
    public String my_page(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("name",service.getMobile(user.getUsername()).getName());
        return "my_page/my_page";
    }

    @GetMapping(value = "/my_pick_page")
    public String my_pick_page(){
        return "my_page/my_pick_page";
    }

    @GetMapping(value = "/recipe_write_page")
    public String recipe_write_page(){
        return "my_page/recipe_write_page";
    }

    @GetMapping(value = "/delete")
    public String delete(@AuthenticationPrincipal User user){
        service.deleteByEmail(user.getUsername());
        return "redirect:/mobile/logout";
    }

    @GetMapping("/update_page")
    public String updatePage(Model model,@AuthenticationPrincipal User user){
        model.addAttribute("mobileFormDto", service.getMobile(user.getUsername()));
        return "login/update_page";
    }

    @PostMapping("/update_page")
    public String newMobile(@Valid MobileFormDto mobileFormDto,@AuthenticationPrincipal User user){
        service.deleteByEmail(user.getUsername());
        Mobile mobile = Mobile.createMobile(mobileFormDto, passwordEncoder);
        service.updateMobile(mobile);
        return "redirect:/user/my_page";
    }
}
