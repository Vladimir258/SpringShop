package ru.fominskiy.entities.mapper;

import org.mapstruct.*;
import ru.fominskiy.entities.Product;
import ru.fominskiy.entities.dto.ProductDto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductDtoMapper {

    ProductDto map(Product product);

    @Mapping(target = "id", ignore = true)
    Product map(ProductDto productDto);
}
