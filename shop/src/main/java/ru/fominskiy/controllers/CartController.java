package ru.fominskiy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fominskiy.persists.Cart;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class CartController {

    private final Cart cart;

    @GetMapping("/addToCart/{id}")
    public String addProductToCart(@PathVariable("id") Long id) {
        cart.add(id);
        return "redirect:/product";
    }

    @GetMapping("/deleteFromCart/{id}")
    public String removeProductFromCart(@PathVariable("id") Long id) {
        cart.remove(id);
        return "redirect:/product";
    }

    @GetMapping("/cart")
    public String getCartPage(Model model) {
        model.addAttribute("cartProducts", cart.getAllProducts());
        return "cart";
    }
}
