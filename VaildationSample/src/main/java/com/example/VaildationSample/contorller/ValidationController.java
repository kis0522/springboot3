package com.example.VaildationSample.contorller;

import com.example.VaildationSample.form.CalcForm;
import com.example.VaildationSample.validator.CalcValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ValidationController {
    /*form-backing bean*/
    @ModelAttribute
    public CalcForm setupForm(){
        return new CalcForm();
    }
    @GetMapping("show")
    public String showView(){
        return "entry";
    }
    @PostMapping("calc")
    public String confirmView(
        @Validated CalcForm form, BindingResult bindingResult, Model model
    ){
        if(bindingResult.hasErrors()){
            return "entry";
        }
        Integer result = form.getLeftNum() + form.getRightNum();
        model.addAttribute("result", result);
        return "confirm";
    }

    @Autowired
    CalcValidator calcValidator;
    @InitBinder("calcForm")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(calcValidator);
    }
}
