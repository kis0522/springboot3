package com.example.SpringMVCViewSample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hello")
//@RequestMapping(value= "hello") 원래 사용하는 형태 (잘 사용하지 않는다.)
//@RequestMapping("hello") 많이 사용하는 형태
//@RequestMapping(value = {"hello","hellospring"}) 값을 2개 이상 넣을 수 있다.
//@RequestMapping(value = "hello", method = RequestMethod.GET) method(form태그)를 사용할 수 있다.
//@RequestMapping(value = "hello", method = {RequestMethod.GET, RequestMethod.POST}) method를 사용해 주고 받을 수 있다.
public class HelloViewController {
    @GetMapping("view")
    //@GetMapping(value={"hello","hellospring"}) 2개이상 배열형태로 넣을 수 있다.
    public String helloView(){
        return "hello";
    }

    @GetMapping("view2")
    public String morningView(){
        return "morning";
    }
}
