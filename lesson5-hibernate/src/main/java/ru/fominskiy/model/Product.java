package ru.fominskiy.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "Select p from Product p"),
        @NamedQuery(name = "countAllProducts", query = "Select count(p) from Product p"),
        @NamedQuery(name = "deleteProductById", query = "delete from Product p where p.id = :id")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private Float cost;

    public Product(String title, Float cost) {
        this.title = title;
        this.cost = cost;
    }
}
