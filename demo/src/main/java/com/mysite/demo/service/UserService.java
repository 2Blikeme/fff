package com.mysite.demo.service;

import com.mysite.demo.entity.*;
import com.mysite.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("clientService")
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    CartService cartService;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return user;
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        //return user;
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles
        );
    }


    public User findUserById(Integer userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null)
            return false;

        if (Objects.equals(user.getUsername(), ""))
            return false;
        if (Objects.equals(user.getPassword(), ""))
            return false;
        if (Objects.equals(user.getEmail(), ""))
            return false;
        if (Objects.equals(user.getAddress(), ""))
            return false;
        if (Objects.equals(user.getCity(), ""))
            return false;

        user.setRole(Role.USER);

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + Role.USER);


        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        cart newCart = cartService.CreateCart(user, new ArrayList<game>(), new ArrayList<playstation>());
        user.setUsersCart(newCart);
        userRepository.save(user);

        return true;
    }

    public boolean deleteUser(Integer userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
