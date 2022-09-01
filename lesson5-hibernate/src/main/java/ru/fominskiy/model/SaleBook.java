package ru.fominskiy.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sale_book")
public class SaleBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @Column
    private Float cost;

    @ManyToOne
    private Product product;

    public SaleBook(Customer customer, Float cost, Product product) {
        this.customer = customer;
        this.cost = cost;
        this.product = product;
    }
}
