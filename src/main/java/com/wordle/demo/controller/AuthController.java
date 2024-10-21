package com.wordle.demo.controller;

import com.wordle.demo.controller.dto.UserDto;
import com.wordle.demo.jwt.JwtTokenUtil;
import com.wordle.demo.service.AuthService;
import com.wordle.demo.repository.entity.UserEntity;
import com.wordle.demo.service.UserService;
import com.wordle.demo.service.model.MyUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;

@RestController
@AllArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/new-user")
    @CrossOrigin
    public String addUser(@RequestBody UserDto userDto) {
        try {
            MyUser user = userService.getUserByUsername(userDto.login());
            return "not ok";
        } catch(UsernameNotFoundException e) {
            authService.addUser(new MyUser(userDto.login(), userDto.password()));
            return jwtTokenUtil.generateToken(userDto.login());
        }

    }

    @PostMapping("/loginPage")
    @CrossOrigin
    public String login(@RequestBody UserDto userDto) {
        try {
            MyUser user = userService.getUserByUsername(userDto.login());

            if(passwordEncoder.matches(userDto.password(), user.getPassword())) {
                return jwtTokenUtil.generateToken(userDto.login());
            } else {
                return "not ok";
            }
        } catch(UsernameNotFoundException e) {
            return "not ok";
        }
    }

    @PostMapping("/checkLogin")
    @CrossOrigin
    public String checkLogin(@RequestBody String token) {
        return jwtTokenUtil.getUsernameFromToken(token);
    }
}
