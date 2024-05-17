package com.example.mobile.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping(value = "user")
public class UserController {
    @GetMapping(value = "/my_page")
    public String my_page(){
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


}
