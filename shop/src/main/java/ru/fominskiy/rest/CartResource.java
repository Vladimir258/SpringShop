package ru.fominskiy.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fominskiy.entities.Cart;
import ru.fominskiy.entities.Product;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartResource {

    private final Cart cart;

    @GetMapping
    public List<Product> getCartPage(Model model) {
        List<Product> pg = cart.getAllProducts();
        return pg;
    }
}
