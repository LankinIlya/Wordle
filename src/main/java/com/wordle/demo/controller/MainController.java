package com.wordle.demo.controller;

import com.wordle.demo.service.AuthService;
import com.wordle.demo.service.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/play")
    public String play(Model model) {
        return "play";
    }
}
