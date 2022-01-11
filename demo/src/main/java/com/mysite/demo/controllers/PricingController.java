package com.mysite.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pricing")
public class PricingController {

    @GetMapping
    public String PricingPage(){
        return "pricing";
    }

}
