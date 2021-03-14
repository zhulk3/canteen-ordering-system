package com.longkai.canteenorderingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello(){
        return "hello,world";
    }
}
