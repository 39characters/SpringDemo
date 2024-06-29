package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class QuizController {

    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model) {
        if ("admin".equals(user.getUsername()) && "password".equals(user.getPassword())) {
            return "redirect:/quiz";
        } else {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/quiz")
    public String showQuizForm(@ModelAttribute("user") User user) {
        if (user.getUsername() == null || !user.getUsername().equals("admin")) {
            return "redirect:/login";
        }
        return "index";
    }

    @PostMapping("/quiz")
    public String submitQuiz(@ModelAttribute("user") User user, Model model) {
        int score = 4; // Assume score is 4 out of 5 for demo purposes
        double percentage = (score / 5.0) * 100;
        model.addAttribute("score", score);
        model.addAttribute("percentage", percentage);
        return "result";
    }
}
