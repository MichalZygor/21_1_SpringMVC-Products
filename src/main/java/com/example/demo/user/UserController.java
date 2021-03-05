package com.example.demo.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profil-edycja")
    public String profileEdit(Model model, Principal principal) {
        model.addAttribute("user", userService.userProfile(principal.getName()));
        return "/user/profile-edit";
    }



    @PostMapping("/profil-aktualizacja")
    public String updateUser(User user, Model model) {
        if (!userService.updateUser(user)) {
            model.addAttribute("user", user);
            model.addAttribute("registerStatus", "userExist");
            return "user/profile-edit";
        } else {
            return "redirect:/";
        }
    }
}
