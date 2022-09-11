package ru.fominskiy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fominskiy.entities.Product;
import ru.fominskiy.entities.dto.ProductDto;
import ru.fominskiy.entities.mapper.ProductDtoMapper;
import ru.fominskiy.repositories.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoMapper mapper;

    public List<ProductDto> productsByTitleAndCost(String titleFilter, BigDecimal costMinFilter, BigDecimal costMaxFilter) {

        return productRepository.productsByTitleAndCost(titleFilter, costMinFilter, costMaxFilter)
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(mapper::map);
        //return productRepository.findById(id).map(ProductService::productToDto);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void save(ProductDto dto) {
        productRepository.save(new Product(dto.getId(), dto.getTitle(), dto.getCost()));
    }

}
