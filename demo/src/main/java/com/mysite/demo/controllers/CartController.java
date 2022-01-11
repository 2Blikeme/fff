package com.mysite.demo.controllers;

import com.mysite.demo.entity.product;
import com.mysite.demo.interfaces.Preorder;
import com.mysite.demo.repo.GameRepository;
import com.mysite.demo.repo.PlaystationRepository;
import com.mysite.demo.repo.ProductRepository;
import com.mysite.demo.service.CartService;
import com.mysite.demo.service.PreorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PlaystationRepository playstationRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PreorderService preorderService;

    @GetMapping
    public String CartPage(Model model, Principal principal){
        model.addAttribute("userCart", cartService.getCartByUser(principal.getName()));
        return "cart";
    }

    @PostMapping("/{id}")
    public String DeleteProduct(Model model, Principal principal,
                                @PathVariable int id){
        product Product = productRepository.findById(id);
        Preorder preorder;
        if (Product.getName().equals("Playstation 5")){
            preorder = playstationRepository.findByName("Playstation 5");
        } else {
            preorder = gameRepository.findByName(Product.getName());
        }
        preorderService.deleteFromUserCart(preorder, principal.getName());

        model.addAttribute("userCart", cartService.getCartByUser(principal.getName()));
        return "cart";
    }

    @PostMapping("/confirm")
    public String ConfirmOrder(Principal principal){
        preorderService.confirmBuy(principal.getName());
        return "redirect:/confirm";
    }
}
