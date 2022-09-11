package ru.fominskiy.entities.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.fominskiy.entities.Product;
import ru.fominskiy.entities.dto.ProductDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-11T22:34:10+0500",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
@Component
public class ProductDtoMapperImpl implements ProductDtoMapper {

    @Override
    public ProductDto map(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        if ( product.getId() != null ) {
            productDto.setId( product.getId() );
        }
        if ( product.getTitle() != null ) {
            productDto.setTitle( product.getTitle() );
        }
        if ( product.getCost() != null ) {
            productDto.setCost( product.getCost() );
        }

        return productDto;
    }

    @Override
    public Product map(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        if ( productDto.getTitle() != null ) {
            product.setTitle( productDto.getTitle() );
        }
        if ( productDto.getCost() != null ) {
            product.setCost( productDto.getCost() );
        }

        return product;
    }
}
