package org.example.filedrop.controllers;

import org.example.filedrop.models.User;
import org.example.filedrop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());  // Tworzymy nowy obiekt użytkownika dla formularza
        return "register"; // Widok rejestracji
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user);  // Przeniesienie logiki do serwisu
            model.addAttribute("message", "Registration successful");
            return "login"; // Przekierowanie do strony logowania po rejestracji
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed");
            return "register"; // Jeśli rejestracja się nie udała, wracamy do formularza
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());  // Tworzymy nowy obiekt użytkownika dla formularza
        return "login"; // Widok logowania
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model) {
        boolean loginSuccess = userService.loginUser(user);
        if (loginSuccess) {
            return "redirect:/dashboard"; // Przekierowanie do strony dashboard po zalogowaniu
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login"; // Powrót do formularza logowania, jeśli nie uda się zalogować
        }
    }
}



