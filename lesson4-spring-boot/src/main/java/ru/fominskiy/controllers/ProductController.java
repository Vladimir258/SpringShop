package ru.fominskiy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fominskiy.persists.Product;
import ru.fominskiy.repositories.ProductRepository;
import ru.fominskiy.services.Cart;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product("", 0f));
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable long id) {
        productRepository.delete(id);
        return "redirect:/product";
    }

    @PostMapping
    public String saveProduct(Product product) {
        productRepository.save(product);
        return "redirect:/product";
    }

    private final Cart cart;

    @GetMapping("/addToCart/{id}")
    public String addProductToCart(@PathVariable("id") Long id) {
        Product product = productRepository.findById(id);
        cart.add(product);
        return "redirect:/product";
    }

    @GetMapping("/deleteFromCart/{id}")
    public String removeProductFromCart(@PathVariable("id") Long id) {
        Product product = productRepository.findById(id);
        cart.remove(product);
        return "redirect:/product";
    }

    @GetMapping("/cart")
    public String getCartPage(Model model) {
        model.addAttribute("cartProducts", cart.getAllProducts());
        return "cart";
    }
}
