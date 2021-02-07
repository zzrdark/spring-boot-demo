package com.zzr.springboot.demo.auto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyContoller {

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/index")
    public String index(Model model){
        model.addAttribute("acceptLanguage","acceptLanguage");
        model.addAttribute("jsessionId","jsessionId");
        model.addAttribute("message","Hello,World");
        return "index";
    }
}
