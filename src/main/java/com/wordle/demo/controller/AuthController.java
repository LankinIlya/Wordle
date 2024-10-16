package com.wordle.demo.controller;

import com.wordle.demo.controller.dto.UserDto;
import com.wordle.demo.service.AuthService;
import com.wordle.demo.repository.entity.UserEntity;
import com.wordle.demo.service.model.MyUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/new-user")
    public String addUser(@RequestBody UserDto userDto) {
        authService.addUser(new MyUser(userDto.login(), userDto.password()));
        return "User is saved";
    }
}
