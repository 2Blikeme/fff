package com.mysite.demo.controllers;

import com.mysite.demo.entity.cart;
import com.mysite.demo.entity.game;
import com.mysite.demo.entity.product;
import com.mysite.demo.interfaces.Preorder;
import com.mysite.demo.repo.GameRepository;
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
@RequestMapping("/games")
public class GamesController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PreorderService preorderService;
    @Autowired
    private GameRepository gameRepository;

    @GetMapping()
    public String GamesPage(Model model){
        Iterable<product> games = productRepository.findAll();
        model.addAttribute("games", games);
        return "games";
    }

    @GetMapping("/bloodborne")
    public String BloodbornePage(Principal principal, Model model){
        product bloodborneFromDb = productRepository.findByName("Bloodborne");
        model.addAttribute("bloodborneFromDb", bloodborneFromDb);
        return "bloodborne";
    }

    @GetMapping("/elden-ring")
    public String EldenRingPage(Model model){
        product eldenRingFromDb = productRepository.findByName("Elden Ring");
        model.addAttribute("eldenRingFromDb", eldenRingFromDb);
        return "elden_ring";
    }

    @GetMapping("/demon-souls")
    public String DemonSoulsPage(Model model){
        product demonSoulsFromDb = productRepository.findByName("Demon's Souls");
        model.addAttribute("demonSoulsFromDb", demonSoulsFromDb);
        return "demon_souls";
    }

    @PostMapping("/bloodborne")
    public String BuyBloodborne(Model model, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }
        Preorder game = gameRepository.findByName("Bloodborne");
        try {
            preorderService.addToUserCart(game, principal.getName());
        } catch (RuntimeException exception){
            model.addAttribute("already_have", "You already have this product");
            product bloodborneFromDb = productRepository.findByName("Bloodborne");
            model.addAttribute("bloodborneFromDb", bloodborneFromDb);
            return "bloodborne";
        }
        return "redirect:/cart";
    }

    @PostMapping("/elden-ring")
    public String BuyEldenRing(Model model, Principal principal){
        if (principal == null){
            return "redirect:/login";
        }
        Preorder game = gameRepository.findByName("Elden Ring");
        try{
            preorderService.addToUserCart(game, principal.getName());
        } catch (RuntimeException exception){
            model.addAttribute("already_have", "You already have this product");
            product eldenRingFromDb = productRepository.findByName("Elden Ring");
            model.addAttribute("eldenRingFromDb", eldenRingFromDb);
            return "elden_ring";
        }
        return "redirect:/cart";
    }

    @PostMapping("/demon-souls")
    public String BuyDemonSouls(Model model, Principal principal){
        if (principal == null){
            return "redirect:/login";
        }
        Preorder game = gameRepository.findByName("Demon's Souls");
        try{
            preorderService.addToUserCart(game, principal.getName());
        } catch (RuntimeException exception){
            model.addAttribute("already_have", "You already have this product");
            product demonSoulsFromDb = productRepository.findByName("Demon's Souls");
            model.addAttribute("demonSoulsFromDb", demonSoulsFromDb);
            return "demon_souls";
        }
        return "redirect:/cart";
    }
}
