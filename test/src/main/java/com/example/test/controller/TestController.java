package com.example.test.controller;

import com.example.test.entity.Test;
import com.example.test.form.TestForm;
import com.example.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService service;

    @ModelAttribute
    public TestForm setUpForm(){
        TestForm form = new TestForm();
        form.setAnswer(true);
        return form;
    }

    @GetMapping
    public String showList(TestForm testForm, Model model){
        testForm.setNewTest(true);
        Iterable<Test> list = service.selectAll();

        model.addAttribute("list",list);
        model.addAttribute("title","등록 폼");
        return "crud";
    }

    @PostMapping("/insert")
    public String insert(@Validated TestForm testForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        Test test = new Test();
        test.setQuestion(testForm.getQuestion());
        test.setAnswer(testForm.getAnswer());
        test.setAuthor(testForm.getAuthor());

        if(!bindingResult.hasErrors()){
            service.insertTest(test);
            redirectAttributes.addFlashAttribute("complete","등록이 완료되었습니다");
            return "redirect:/test/c";
        }else{
            return showList(testForm, model);
        }
    }
    @GetMapping("/{id}")
    public String showUpdate(TestForm testForm, @PathVariable Integer id, Model model){
        Optional<Test> testOpt = service.selectOneById(id);
        Optional<TestForm> testFormOpt = testOpt.map(t -> makeTestForm(t));
        if(testFormOpt.isPresent()){
            testForm = testFormOpt.get();
        }
        makeUpdateModel(testForm, model);
        return "crud";
    }
    private void makeUpdateModel(TestForm testForm, Model model){
        model.addAttribute("id",testForm.getId());
        testForm.setNewTest(false);
        model.addAttribute("testForm",testForm);
        model.addAttribute("title","변경 폼");
    }

    @PostMapping("/update")
    public String update(@Validated TestForm testForm, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        Test test = makeTest(testForm);
        if(!result.hasErrors()){
            service.updateTest(test);
            redirectAttributes.addFlashAttribute("complete","변경이 완료되었습니다");
            return "redirect:/test/" + test.getId();
        }else{
            makeUpdateModel(testForm,model);
            return "crud";
        }
    }

    public Test makeTest(TestForm testForm){
        Test test = new Test();
        test.setId(testForm.getId());
        test.setQuestion(testForm.getQuestion());
        test.setAnswer(testForm.getAnswer());
        test.setAuthor(testForm.getAuthor());
        return test;
    }

    public TestForm makeTestForm(Test test){
        TestForm form = new TestForm();
        form.setId(test.getId());
        form.setQuestion(test.getQuestion());
        form.setAnswer(test.getAnswer());
        form.setAuthor(test.getAuthor());
        form.setNewTest(false);
        return form;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes){
        service.deleteTestById(Integer.parseInt(id));
        redirectAttributes.addFlashAttribute("delcomplete","삭제 완료했습니다");
        return "redirect:/test";
    }

    Integer nextId = 0;
    @GetMapping("/play")
    public String showTest(TestForm testForm, Model model){
        Optional<Test> testOpt = service.selectOneById(++nextId);

        if(testOpt.isPresent()){
            Optional<TestForm> testFormOpt = testOpt.map(t -> makeTestForm(t));
            testForm = testFormOpt.get();
        }else{
            model.addAttribute("msg","등록된 문제가 없습니다");
            nextId  = 0;
            return "play";
        }
        model.addAttribute("testForm",testForm);
        return "play";
    }

    @GetMapping("/c")
    public String create(){
        return "c";
    }
    @GetMapping("/ud")
    public String ud(TestForm testForm, Model model){
        testForm.setNewTest(true);
        Iterable<Test> list = service.selectAll();

        model.addAttribute("list",list);
        model.addAttribute("title","변경 폼");
        return "ud";
    }

    @PostMapping("/check")
    public String checkTest(TestForm testForm, @RequestParam Boolean answer, Model model){
        if(service.checkTest(testForm.getId(), answer)){
            model.addAttribute("msg","정답입니다.");
        }else{
            model.addAttribute("msg","오답입니다.");
        }
        return "answer";
    }
}
