package com.mysite.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/confirm")
public class ConfirmController {
    @GetMapping
    public String confirmPage(){
        return "confirm";
    }
}
