package com.zzr.springboot.demo.manual.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){

        return "HelloWorld";
    }

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("acceptLanguage","acceptLanguage");
        model.addAttribute("jsessionId","jsessionId");
        model.addAttribute("message","Hello,World");
        System.out.println("entry");
        return "index";
    }
}
