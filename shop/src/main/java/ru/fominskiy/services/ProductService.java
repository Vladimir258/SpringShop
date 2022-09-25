package ru.fominskiy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.fominskiy.entities.Product;
import ru.fominskiy.entities.dto.ProductDto;
import ru.fominskiy.entities.mapper.ProductDtoMapper;
import ru.fominskiy.repositories.ProductRepository;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoMapper mapper;

    public Page<ProductDto> productsByTitleAndCost(String titleFilter, BigDecimal costMinFilter, BigDecimal costMaxFilter, Integer page, Integer size, String sortField) {

        return productRepository.productsByTitleAndCost(titleFilter, costMinFilter, costMaxFilter, PageRequest.of(page, size, Sort.by(sortField)))
                .map(mapper::map);
    }

    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(mapper::map);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void save(ProductDto dto) {
        productRepository.save(new Product(dto.getId(), dto.getTitle(), dto.getCost()));
    }

}
