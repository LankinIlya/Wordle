package com.wordle.demo.controller;

import com.wordle.demo.controller.dto.UserDto;
import com.wordle.demo.jwt.JwtTokenUtil;
import com.wordle.demo.service.AuthService;
import com.wordle.demo.service.UserService;
import com.wordle.demo.service.model.MyUser;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails
        .UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    public String addUser(@RequestBody UserDto userDto,
                          HttpServletResponse response) {
        try {
            userService.getUserByUsername(userDto.login());
            return "not ok";
        } catch(UsernameNotFoundException e) {
            authService.addUser(new MyUser(userDto.login(),
                                            userDto.password(),
                                             userDto.games(), userDto.wins()));
            MyUser user = userService.getUserByUsername(userDto.login());

            response.setHeader("Access-Control-Allow-Credentials", "true");
            return jwtTokenUtil.generateToken(userDto.login(), user.getId());
        }

    }

    @PostMapping("/loginPage")
    @CrossOrigin
    public String login(@RequestBody UserDto userDto,
                        HttpServletResponse response) {
        try {
            MyUser user = userService.getUserByUsername(userDto.login());

            if(passwordEncoder.matches(userDto.password(),
                                    user.getPassword())) {
                response.setHeader("Access-Control-Allow-Credentials",
                                    "true");
                return jwtTokenUtil.generateToken(userDto.login(),
                                                    user.getId());
            } else {
                return "not ok";
            }
        } catch(UsernameNotFoundException e) {
            return "not ok";
        }
    }

    @PostMapping("/checkLogin")
    @CrossOrigin
    public String checkLogin(@CookieValue("jwt") String token,
                             HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return jwtTokenUtil.getUsernameFromToken(token) + " - "
                + jwtTokenUtil.getIdFromToken(token).toString();
    }
}
