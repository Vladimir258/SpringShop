package ru.fominskiy.entities.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    private String title;

    private BigDecimal cost;

    public ProductDto(String s, BigDecimal bigDecimal) {
        title = s;
        cost = bigDecimal;
    }
}
