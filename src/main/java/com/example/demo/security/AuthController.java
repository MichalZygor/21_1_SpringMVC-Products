package com.example.demo.security;

import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/logowanie")
    public String loginForm(){
        return "security/login";
    }

    @GetMapping("/rejestracja")
    public String registeerForm(Model model){
        model.addAttribute("user", new User());
        return "security/registerForm";
    }

    @PostMapping("/rejestracja")
    public String addUser(User user){
        if (!userService.registerUser(user)){
            return "redirect:/rejestracja?error";
        };

        return "redirect:/logowanie";
    }
}
