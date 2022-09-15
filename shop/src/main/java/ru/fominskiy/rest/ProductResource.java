package ru.fominskiy.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fominskiy.entities.dto.ProductDto;
import ru.fominskiy.exceptions.EntityNotFoundException;
import ru.fominskiy.services.ProductService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductDto form(@PathVariable("id") long id, Model model) {
        ProductDto dto = productService.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Product not found"));
        return dto;
    }

    @GetMapping
    public List<ProductDto> listPage(@RequestParam(required = false) String titleFilter,
                         @RequestParam(required = false) BigDecimal costMinFilter,
                         @RequestParam(required = false) BigDecimal costMaxFilter,
                         @RequestParam(required = false) Optional<Integer> page,
                         @RequestParam(required = false) Optional<Integer> size,
                         @RequestParam(required = false) Optional<String> sortField,
                         Model model) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        String sortFieldValue = sortField.filter(s -> !s.isBlank()).orElse("id");
        titleFilter = titleFilter == null || titleFilter.isBlank() ? null : "%" + titleFilter.trim() + "%";
        costMinFilter = costMinFilter == null ? new BigDecimal(0) : costMinFilter;
        costMaxFilter = costMaxFilter == null ? new BigDecimal(Double.MAX_VALUE) : costMaxFilter;

        Page<ProductDto> dto = productService.productsByTitleAndCost(titleFilter, costMinFilter, costMaxFilter, pageValue, sizeValue, sortFieldValue);
        return dto.get().collect(Collectors.toList());
    }

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto dto) {
        if(dto.getId() != null) {
            throw new IllegalArgumentException("Created product shouldn't have id");
        }
        productService.save(dto);
        return dto;
    }

    @GetMapping("/new")
    public ProductDto addNewProduct(@RequestBody ProductDto dto) {
        if(dto == null) {
            return new ProductDto("", new BigDecimal(0));
        } else {
            return dto;
        }
    }

    @DeleteMapping("{id}")
    public void deleteProductById(@RequestBody long id) {
        productService.deleteById(id);
    }
}
