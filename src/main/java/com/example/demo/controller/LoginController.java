package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // Hiển thị trang đăng nhập
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    // Xử lý đăng nhập
    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        User user = userService.login(username, password);

        if (user != null) {
            session.setAttribute("account", user);
            return "redirect:/home";
        }

        model.addAttribute("message", "Tên đăng nhập hoặc mật khẩu không chính xác!");
        return "login";
    }
}