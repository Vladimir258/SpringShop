package ru.fominskiy.controllers;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.fominskiy.exceptions.EntityNotFoundException;
import ru.fominskiy.persists.Product;
import ru.fominskiy.repositories.InDBProductRepository;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final InDBProductRepository productRepository;

    public ProductController(InDBProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Не удаляю для наглядности
//    @GetMapping
//    public String listPage(@RequestParam Optional<String> productFilter, Model model) {
//        if(productFilter.isEmpty() || productFilter.get().isBlank()) {
//            model.addAttribute("products", productRepository.findAll());
//        } else {
//            model.addAttribute("products", productRepository.productsByTitle("%" + productFilter.get() + "%"));
//        }
//        return "product";
//    }

    @GetMapping
    public String listPage(@RequestParam(required = false) String titleFilter,
                           @RequestParam(required = false) BigDecimal costMinFilter,
                           @RequestParam(required = false) BigDecimal costMaxFilter, Model model) {
        titleFilter = titleFilter == null || titleFilter.isBlank() ? null : "%" + titleFilter.trim() + "%";
        costMinFilter = costMinFilter == null ? new BigDecimal(0) : costMinFilter;
        costMaxFilter = costMaxFilter == null ? new BigDecimal(Double.MAX_VALUE) : costMaxFilter;
     //   model.addAttribute("products", productRepository.productsByTitle(titleFilter));
        model.addAttribute("products", productRepository.productsByTitleAndCost(titleFilter, costMinFilter, costMaxFilter));
        return "product";
    }


    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Product not found")));
        return "product_form";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product("", new BigDecimal(0)));
        return "product_form";
    }

    @DeleteMapping("{id}")
    public String deleteProductById(@PathVariable long id) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(Model model, EntityNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "not_found";
    }
}
