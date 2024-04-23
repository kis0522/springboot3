package com.example.project06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
    @GetMapping("main")
    public String index(){
        return "index";
    }

    @GetMapping("popup")
    public String popup(){
        return "popup";
    }
    @GetMapping("sub01")
    public String sub01(){
        return "sub01";
    }
    @GetMapping("sub02")
    public String sub02(){
        return "sub02";
    }
    @GetMapping("sub03")
    public String sub03(){
        return "sub03";
    }
}
