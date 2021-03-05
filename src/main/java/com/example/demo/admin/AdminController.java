package com.example.demo.admin;

import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/administracja")
    public String adminPanel(Model model) {
        List<User> userList = userService.findAllWithoutLoggedUser();
        model.addAttribute("userList", userList);
        return "user/admin";
    }

    @GetMapping("/profil-usuniecie")
    public String profilDelete(@RequestParam(required = true) Long id) {
        userService.deleteUserById(id);
        return "redirect:/administracja";
    }

    @GetMapping("/dodaj-role")
    public String addRoleAdmin(@RequestParam(required = true) String login) {
        userService.addRoleAdmin(login);
        return "redirect:/administracja";
    }

    @GetMapping("/usun-role")
    public String removeRoleAdmin(@RequestParam(required = true) String login) {
        userService.removeRoleAdmin(login);
        return "redirect:/administracja";
    }
}
