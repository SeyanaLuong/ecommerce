package com.seyanaluong.ecommerce.controller;

import com.seyanaluong.ecommerce.model.CartItem;
import com.seyanaluong.ecommerce.model.Order;
import com.seyanaluong.ecommerce.service.CustomerService;
import com.seyanaluong.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String index(HttpSession session) {
        if (session.getAttribute("cart") == null) {
            return "cart/cart";
        }
        HashMap<Integer, CartItem> cart = (HashMap<Integer, CartItem>) session.getAttribute("cart");
        session.setAttribute("cart", cart);
        return "cart/cart";
    }

    @GetMapping("/checkout")
    public String checkoutIndex(Order order, Model model, Principal principal) {
        boolean exist = customerService.doesCustomerExist(principal.getName());
        if(exist) {
            model.addAttribute("customer", customerService.getCustomerByUsername(principal.getName()));
            model.addAttribute("order", new Order());
            return "cart/checkout";
        }
        return "redirect:/customer";
    }

    @GetMapping("/buy/{id}")
    public String addToCart(@PathVariable int id, HttpSession session, Principal principal) {
        if (session.getAttribute("cart") == null) {
            HashMap<Integer, CartItem> cart = new HashMap<>();
            cart.put(id, new CartItem(productService.getProduct(id), 1, principal.getName()));
            session.setAttribute("cart", cart);
        } else {
            HashMap<Integer, CartItem> cart = (HashMap<Integer, CartItem>) session.getAttribute("cart");
            if (cart.containsKey(id)) {
                CartItem cartItemUpdate = cart.get(id);
                cartItemUpdate.setQuantity(cartItemUpdate.getQuantity() + 1);
                cart.put(id, cartItemUpdate);
            } else {
                cart.put(id, new CartItem(productService.getProduct(id), 1, principal.getName()));
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable int id, HttpSession session) {
        HashMap<Integer, CartItem> cart = (HashMap<Integer, CartItem>) session.getAttribute("cart");
        cart.remove(id);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @GetMapping("/update")
    public String updateCartForm(HttpSession session) {
        HashMap<Integer, CartItem> cart = (HashMap<Integer, CartItem>) session.getAttribute("cart");
        session.setAttribute("cart", cart);
        return "cart/update-cart";
    }

    @GetMapping("/update/{id}/{quantity}")
    public String updateCart(@PathVariable int id, @PathVariable int quantity, HttpSession session) {
        if (quantity < 1) {
            return removeFromCart(id, session);
        }
        HashMap<Integer, CartItem> cart = (HashMap<Integer, CartItem>) session.getAttribute("cart");
        CartItem cartItemUpdate = cart.get(id);
        cartItemUpdate.setQuantity(quantity);
        cart.put(id, cartItemUpdate);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

}
