package ru.fominskiy.persists;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;

@Getter
@Setter
public class Product {

    private Long id;
    @NotBlank(message = "can not be empty")
    private String title;
    @Positive(message = "not null or negative")
    @Max(value = 1000, message = "not > 1000")
    private Float cost;

    public Product(String title, Float cost) {
        this.title = title;
        this.cost = cost;
    }
}
