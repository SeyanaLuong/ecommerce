package com.seyanaluong.ecommerce.controller;

import com.seyanaluong.ecommerce.service.CustomerService;
import com.seyanaluong.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeResource {

    @Autowired
    private ProductService productService;

    @GetMapping({"/", "/index"})
    public String home(Model model, Authentication authentication) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

}
