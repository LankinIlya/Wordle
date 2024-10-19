package com.wordle.demo.controller;

import com.wordle.demo.controller.dto.UserDto;
import com.wordle.demo.service.AuthService;
import com.wordle.demo.repository.entity.UserEntity;
import com.wordle.demo.service.UserService;
import com.wordle.demo.service.model.MyUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/new-user")
    public String addUser(@RequestBody UserDto userDto) {
        authService.addUser(new MyUser(userDto.login(), userDto.password()));
        return "User is saved";
    }

    @PostMapping("/loginPage")
    @CrossOrigin
    public String login(@RequestBody UserDto userDto) {
        try {
            MyUser user = userService.getUserByUsername(userDto.login());

            if(passwordEncoder.matches(userDto.password(), user.getPassword())) {
                return "ok";
            } else {
                return "not ok";
            }
        } catch(UsernameNotFoundException e) {
            return "not ok";
        }


    }
}
