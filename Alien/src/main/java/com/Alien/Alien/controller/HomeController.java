package com.Alien.Alien.controller;

import com.Alien.Alien.model.User;
import com.Alien.Alien.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@RequestParam("username") String username,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 @RequestParam(value = "role", defaultValue = "student") String role,
                                 Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "register";
        }

        if (password.length() < 8) {
            model.addAttribute("error", "Password must be at least 8 characters long!");
            return "register";
        }

        if (userService.existsByUsername(username)) {
            model.addAttribute("error", "Username already taken!");
            return "register";
        }

        if (userService.existsByEmail(email)) {
            model.addAttribute("error", "Email already registered!");
            return "register";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(password);
        user.setRole(role);

        userService.registerUser(user);

        return "redirect:/home";
    }
    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model,
                            HttpSession session) {

        User user = userService.findByEmail(email);

        if (user == null || user.getPasswordHash() == null) {
            model.addAttribute("error", "Invalid email or password!");
            return "login";
        }

        if (!password.equals(user.getPasswordHash())) {
            model.addAttribute("error", "Invalid email or password!");
            return "login";
        }

        session.setAttribute("loggedInUser", user);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    @GetMapping("/home")
    public String home() {
        return "home"; // create home.html
    }

}
