package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {

        if (session.getAttribute("account") == null) {
            return "redirect:/login";
        }

        User account = (User) session.getAttribute("account");

        model.addAttribute("account", account);

        boolean isAdmin = "ADMIN".equals(account.getRole());
        model.addAttribute("isAdmin", isAdmin);

        // Danh sách sản phẩm mẫu
        List<Map<String, Object>> products = new ArrayList<>();

        products.add(Map.of(
                "id", 1,
                "name", "Arduino Uno R3",
                "price", 150000,
                "stock", 25,
                "image", "https://placehold.co/300x200?text=Arduino+Uno"
        ));

        products.add(Map.of(
                "id", 2,
                "name", "ESP32 DevKit V1",
                "price", 120000,
                "stock", 18,
                "image", "https://placehold.co/300x200?text=ESP32"
        ));

        products.add(Map.of(
                "id", 3,
                "name", "Arduino Nano",
                "price", 85000,
                "stock", 30,
                "image", "https://placehold.co/300x200?text=Arduino+Nano"
        ));

        products.add(Map.of(
                "id", 4,
                "name", "Cảm biến nhiệt độ DHT11",
                "price", 35000,
                "stock", 40,
                "image", "https://placehold.co/300x200?text=DHT11"
        ));

        model.addAttribute("products", products);

        return "home";
    }
}