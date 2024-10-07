package com.wordle.demo.controller;

import com.wordle.demo.service.AuthService;
import com.wordle.demo.service.model.MyUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/new-user")
    public String addUser(@RequestBody MyUser user) {
        authService.addUser(user);
        return "User is saved";
    }
}
