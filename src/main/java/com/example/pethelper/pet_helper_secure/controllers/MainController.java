package com.example.pethelper.pet_helper_secure.controllers;

import com.example.pethelper.pet_helper_secure.dao.UserDao;

import com.example.pethelper.pet_helper_secure.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class MainController {


    private UserDao userDao;
    private PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String home() {
        System.out.println("home");
        return "home";
    }

//    @GetMapping("/users")
//    public List<User> users(){
//        return Arrays.asList(new User("xxx","xxx"),new User("yyy","yyy"));
//    }

    @PostMapping("/save")
    public User save(User user) {
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user);
        userDao.save(user);
        return user;
    }


}


