package com.mysite.demo.controllers;

import com.mysite.demo.entity.product;
import com.mysite.demo.interfaces.Preorder;
import com.mysite.demo.repo.PlaystationRepository;
import com.mysite.demo.repo.ProductRepository;
import com.mysite.demo.service.PreorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/playstation")
public class PlaystationController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PlaystationRepository playstationRepository;
    @Autowired
    private PreorderService preorderService;

    @GetMapping()
    public String PlaystationPage(Model model){
        product Playstation = productRepository.findByName("Playstation 5");
        model.addAttribute("playstaion5", Playstation);
        return "playstation";
    }

    @PostMapping()
    public String BuyPlaystation(Model model, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }
        Preorder playstation = playstationRepository.findByName("Playstation 5");
        try {
            preorderService.addToUserCart(playstation, principal.getName());
        } catch (RuntimeException exception){
            model.addAttribute("already_have", "You already have this product");
            product Playstation = productRepository.findByName("Playstation 5");
            model.addAttribute("playstaion5", Playstation);
            return "playstation";
        }
        return "redirect:/cart";

    }
}
