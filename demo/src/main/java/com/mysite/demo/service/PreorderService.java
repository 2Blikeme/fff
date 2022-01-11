package com.mysite.demo.service;

import com.mysite.demo.entity.*;
import com.mysite.demo.interfaces.Preorder;
import com.mysite.demo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("preorder")
public class PreorderService {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    GamesPreordersRepository gamesPreordersRepository;
    @Autowired
    PlaystationPreorderRepository playstationPreorderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;


    public void addToUserCart(Preorder preorder, String username){
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (user.getUsersCart() == null){
            cart newCart = cartService.CreateCart(user, new ArrayList<game>(), new ArrayList<playstation>());
            user.setUsersCart(newCart);
            userService.saveUser(user);
        }
        cart Cart = user.getUsersCart();
        cartService.addPreorders(Cart, new ArrayList<>(Collections.singleton(preorder)));
    }

    public void deleteFromUserCart(Preorder preorder, String username){
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        cart Cart = user.getUsersCart();
        cartService.deletePreorders(Cart, new ArrayList<>(Collections.singleton(preorder)));
    }

    public void confirmBuy(String username){
        User user = userRepository.findByUsername(username);
        cart userCart = user.getUsersCart();
        List<game> gameList = userCart.getGamesPreorders();
        List<playstation> playstationList = userCart.getPlaystationPreorders();
        if (gameList.isEmpty() && playstationList.isEmpty()){
            throw new RuntimeException("Cart is empty");
        } else {
            for (game userGame : gameList) {
                games_preorder preorder = new games_preorder();
                preorder.setUserId(user.getId());
                preorder.setGameId(userGame.getId());
                gamesPreordersRepository.save(preorder);
            }
            for (playstation userPlaystation : playstationList) {
                playstation_preorder preorder = new playstation_preorder();
                preorder.setUserId(user.getId());
                preorder.setPsId(userPlaystation.getId());
                playstationPreorderRepository.save(preorder);
            }
        }
        userCart.setGamesPreorders(null);
        userCart.setPlaystationPreorders(null);
        cartRepository.save(userCart);
    }
}

