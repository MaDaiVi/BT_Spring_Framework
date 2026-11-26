package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    // Hiển thị trang đăng ký
    @GetMapping("/register")
    public String showRegister(Model model) {

        model.addAttribute("user", new User());

        return "register";
    }

    // Xử lý đăng ký
    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {

        String result = userService.register(user);

        if (result.equals("Đăng ký thành công!")) {
            model.addAttribute("message", result);
            return "login";
        }

        model.addAttribute("message", result);

        return "register";
    }
}