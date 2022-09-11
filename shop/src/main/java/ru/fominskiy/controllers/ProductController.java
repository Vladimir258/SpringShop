package ru.fominskiy.controllers;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.fominskiy.entities.dto.ProductDto;
import ru.fominskiy.exceptions.EntityNotFoundException;
import ru.fominskiy.services.ProductService;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listPage(@RequestParam(required = false) String titleFilter,
                           @RequestParam(required = false) BigDecimal costMinFilter,
                           @RequestParam(required = false) BigDecimal costMaxFilter,
                           @RequestParam(required = false) Optional<Integer> page,
                           @RequestParam(required = false) Optional<Integer> size,
                           Model model) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        titleFilter = titleFilter == null || titleFilter.isBlank() ? null : "%" + titleFilter.trim() + "%";
        costMinFilter = costMinFilter == null ? new BigDecimal(0) : costMinFilter;
        costMaxFilter = costMaxFilter == null ? new BigDecimal(Double.MAX_VALUE) : costMaxFilter;

        model.addAttribute("products", productService.productsByTitleAndCost(titleFilter, costMinFilter, costMaxFilter, pageValue, sizeValue));
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productService.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Product not found")));
        return "product_form";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new ProductDto("", new BigDecimal(0)));
        return "product_form";
    }

    @DeleteMapping("{id}")
    public String deleteProductById(@PathVariable long id) {
        productService.deleteById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String saveProduct(@Valid ProductDto productDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "product_form";
        }
        productService.save(productDto);
        return "redirect:/product";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(Model model, EntityNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "not_found";
    }

}
