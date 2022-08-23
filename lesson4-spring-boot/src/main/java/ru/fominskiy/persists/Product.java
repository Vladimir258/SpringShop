package ru.fominskiy.persists;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private Long id;
    private String title;

    private Float cost;

    public Product(String title, Float cost) {
        this.title = title;
        this.cost = cost;
    }
}
