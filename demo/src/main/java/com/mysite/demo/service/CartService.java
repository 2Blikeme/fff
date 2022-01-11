package com.mysite.demo.service;

import com.mysite.demo.dao.CartDao;
import com.mysite.demo.entity.*;
import com.mysite.demo.interfaces.Preorder;
import com.mysite.demo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("cart")
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlaystationRepository playstationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


    public cart CreateCart(User user, List<game> gameList, List<playstation> playstationList) {
        cart userCart = new cart();
        userCart.setUser(user);
        userCart.setGamesPreorders(gameList);
        userCart.setPlaystationPreorders(playstationList);
        return cartRepository.save(userCart);
    }


    public void addPreorders(cart Cart, List<Preorder> preorderList) {
        ArrayList<Preorder> preorders = new ArrayList<>(preorderList);
        if (preorders.get(0).getCategory().equals("game")) {
            List<game> usersGames = Cart.getGamesPreorders();
            List<game> newUserGames = usersGames == null ? new ArrayList<>() : new ArrayList<>(usersGames);
            for (Preorder preorder : preorders) {
                game preorderGame = gameRepository.findById(preorder.getId());
                if (newUserGames.contains(preorderGame)) {
                    throw new RuntimeException("Already have");
                } else {
                    newUserGames.add(gameRepository.findById(preorder.getId()));
                }
            }
            Cart.setGamesPreorders(newUserGames);
        } else if (preorders.get(0).getCategory().equals("playstation")) {
            List<playstation> usersPlaystation = Cart.getPlaystationPreorders();
            List<playstation> newUserPlaystation = usersPlaystation == null ? new ArrayList<>() : new ArrayList<>(usersPlaystation);
            for (Preorder preorder : preorders) {
                playstation preorderPlaystation = playstationRepository.findById(preorder.getId());
                if (newUserPlaystation.contains(preorderPlaystation)) {
                    throw new RuntimeException("Already have");
                } else {
                    newUserPlaystation.add(playstationRepository.findById(preorder.getId()));
                }
            }
            Cart.setPlaystationPreorders(newUserPlaystation);
        }
        cartRepository.save(Cart);
    }


    public CartDao getCartByUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User " + username + "not found");
        }
        cart Cart = user.getUsersCart();
        CartDao cartDao = new CartDao();

        ArrayList<product> productArrayList = new ArrayList<>();
        for (game userGame :
                Cart.getGamesPreorders()) {
            productArrayList.add(productRepository.findById(userGame.getDescription()));
        }
        for (playstation userPlaystation :
                Cart.getPlaystationPreorders()) {
            productArrayList.add(productRepository.findById(userPlaystation.getDescription()));
        }
        cartDao.setProductList(productArrayList);
        cartDao.calcSum();
        return cartDao;
    }


    public void deletePreorders(cart Cart, List<Preorder> preorderList) {
        ArrayList<Preorder> preorders = new ArrayList<>(preorderList);
        if (preorders.get(0).getCategory().equals("game")) {
            List<game> usersGames = Cart.getGamesPreorders();
            List<game> newUserGames = new ArrayList<>();
            for (Preorder preorder : usersGames) {
                game preorderGame = gameRepository.findById(preorder.getId());
                if (!preorders.contains(preorderGame)) {
                    newUserGames.add(preorderGame);
                }
                //gamesPreordersRepository.delete(new games_preorder(Cart.getUser().getId(), preorder.getId()));
            }
            Cart.setGamesPreorders(newUserGames);
        } else if (preorders.get(0).getCategory().equals("playstation")) {
            List<playstation> usersPlaystation = Cart.getPlaystationPreorders();
            List<playstation> newUserPlaystation = new ArrayList<>();
            for (Preorder preorder : usersPlaystation) {
                playstation preorderPlaystation = playstationRepository.findById(preorder.getId());
                if (!preorders.contains(preorderPlaystation)) {
                    newUserPlaystation.add(preorderPlaystation);
                }
                //plaPreordersRepository.delete(new games_preorder(Cart.getUser().getId(), preorder.getId()));
            }
            Cart.setPlaystationPreorders(newUserPlaystation);
        }
        cartRepository.save(Cart);
    }
}