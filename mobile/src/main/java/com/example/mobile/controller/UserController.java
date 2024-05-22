package com.example.mobile.controller;

import com.example.mobile.service.MobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "user")
public class UserController {
    private final MobileService service;

    @GetMapping(value = "/my_page")
    public String my_page(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("name",service.getNameByEmail(user.getUsername()));
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
    public String updatePage(){
        return "login/update_page";
    }

}
