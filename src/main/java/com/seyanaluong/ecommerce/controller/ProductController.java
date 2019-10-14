package com.seyanaluong.ecommerce.controller;

import com.seyanaluong.ecommerce.model.Product;
import com.seyanaluong.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/electronics")
    public String electronics(Model model) {
        model.addAttribute("products", productService.getAllProductsByDepartment("electronics"));
        return "electronics/all";
    }

    @GetMapping("/electronics/games")
    public String games(Model model) {
        model.addAttribute("products", productService.getAllProductsByCategory("games"));
        return "electronics/games";
    }

    @GetMapping("/electronics/computers")
    public String computers(Model model) {
        model.addAttribute("products", productService.getAllProductsByCategory("computers"));
        return "electronics/computers";
    }

    @GetMapping("/electronics/tvs")
    public String tvs(Model model) {
        model.addAttribute("products", productService.getAllProductsByCategory("tvs"));
        return "electronics/tvs";
    }

    @GetMapping("/furniture")
    public String furniture(Model model) {
        model.addAttribute("products", productService.getAllProductsByDepartment("furniture"));
        return "furniture/all";
    }

    @GetMapping("/furniture/bedroom")
    public String bedroom(Model model) {
        model.addAttribute("products", productService.getAllProductsByCategory("bedroom"));
        return "furniture/bedroom";
    }

    @GetMapping("/furniture/bathroom")
    public String bathroom(Model model) {
        model.addAttribute("products", productService.getAllProductsByCategory("bathroom"));
        return "furniture/bathroom";
    }

    @GetMapping("/furniture/outdoor")
    public String outdoor(Model model) {
        model.addAttribute("products", productService.getAllProductsByCategory("outdoor"));
        return "furniture/outdoor";
    }

}
