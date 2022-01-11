package com.mysite.demo.controllers;

import com.mysite.demo.entity.User;
import com.mysite.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping
    public String RegistrationPage(){
        return "registration";
    }

    @PostMapping
    public String Registration(@RequestParam String login,
                               @RequestParam String password,
                               @RequestParam String email,
                               @RequestParam String address,
                               @RequestParam String city,
                               Model model
                               ){
        User new_user = new User();
        new_user.setUsername(login);
        new_user.setPassword(password);
        new_user.setEmail(email);
        new_user.setAddress(address);
        new_user.setCity(city);
        if (!userService.saveUser(new_user)){
            model.addAttribute("registrationError", "Corrupted info");
            return "registration";
        }
        return "redirect:/login";
    }
}

