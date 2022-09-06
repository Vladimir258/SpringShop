package ru.fominskiy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fominskiy.persists.Product;
import ru.fominskiy.repositories.ProductRepository;

import java.util.List;

//@Controller
//@Repository
//@RequestMapping("/cartService")
//@Scope("prototype")
//@RequiredArgsConstructor
public class CartService {

//    private final Cart cart;
//    private final ProductRepository productRepository;
//
//    @GetMapping("/addToCart/{id}")
//    public String addProductToCart(@PathVariable("id") Long id) {
//        Product product = productRepository.findById(id);
//        cart.add(product);
//        return "product";
//    }
//
//    @GetMapping("/deleteFromCart/{id}")
//    public String removeProductFromCart(@PathVariable("id") Long id) {
//        Product product = productRepository.findById(id);
//        cart.remove(product);
//        return "product";
//    }
//
//    @GetMapping("/cart")
//    public String getCartPage(Model model) {
//        model.addAttribute("cartProducts", cart.getAllProducts());
//        return "cart";
//    }

}
