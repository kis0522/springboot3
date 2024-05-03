package com.example.project06.controller;

import com.example.project06.entity.Test;
import com.example.project06.form.TestForm;
import com.example.project06.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Optional;

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

    @Autowired
    TestService service;

    @GetMapping("/sub04")
    public String showList(TestForm testForm, Model model){
        testForm.setNewTest(true);
        Iterable<Test> list = service.selectAll();
        model.addAttribute("list",list);
        model.addAttribute("title","등록 폼");
        return "sub04";
    }
    @GetMapping("/sub05")
    public String sub05(TestForm testForm, Model model){
        testForm.setNewTest(true);
        Iterable<Test> list = service.selectAll();
        model.addAttribute("list",list);
        return "sub05";
    }
    @PostMapping("sub04/insert")
    public String insert(@Validated TestForm testForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        Test test = new Test();
        test.setTitle(testForm.getTitle());
        test.setPrice(testForm.getPrice());
        test.setUser(testForm.getUser());
        LocalDate localDate = LocalDate.now();
        test.setDate(localDate);

        if(!bindingResult.hasErrors()){
            service.insertTest(test);
            redirectAttributes.addFlashAttribute("complete","등록이 완료되었습니다");
            return "redirect:/sub04";
        }else{
            return sub05(testForm, model);
        }
    }
    @GetMapping("sub05/{id}")
    public String showUpdate(TestForm testForm, @PathVariable Integer id, Model model){
        Optional<Test> testOpt = service.selectOneById(id);
        Optional<TestForm> testFormOpt = testOpt.map(t -> makeTestForm(t));
        model.addAttribute("modify","modify");

        if(testFormOpt.isPresent()){
            testForm = testFormOpt.get();
        }
        makeUpdateModel(testForm, model);
        return "sub05";
    }

    private void makeUpdateModel(TestForm testForm, Model model){
        model.addAttribute("id",testForm.getId());
        testForm.setNewTest(false);
        model.addAttribute("testForm",testForm);
        model.addAttribute("title","변경 폼");
    }

    @PostMapping("sub05/update")
    public String update(@Validated TestForm testForm, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        Test test = makeTest(testForm);
        if(!result.hasErrors()){
            service.updateTest(test);
            redirectAttributes.addFlashAttribute("complete","수정이 완료되었습니다");
            return "redirect:/sub05/" + test.getId();
        }else{
            makeUpdateModel(testForm,model);
            return "sub04";
        }
    }
    public Test makeTest(TestForm testForm){
        Test test = new Test();
        test.setId(testForm.getId());
        LocalDate localDate = LocalDate.now();
        test.setDate(localDate);
        test.setUser(testForm.getUser());
        test.setTitle(testForm.getTitle());
        test.setPrice(testForm.getPrice());
        return test;
    }
    public TestForm makeTestForm(Test test){
        TestForm form = new TestForm();
        form.setId(test.getId());
        form.setDate(test.getDate());
        form.setUser(test.getUser());
        form.setTitle(test.getTitle());
        form.setPrice(test.getPrice());
        form.setNewTest(false);
        return form;
    }
    @PostMapping("sub05/delete")
    public String delete(@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes){
        service.deleteTestById(Integer.parseInt(id));
        redirectAttributes.addFlashAttribute("delcomplete","삭제 완료했습니다");
        return "redirect:/sub04";
    }
}
