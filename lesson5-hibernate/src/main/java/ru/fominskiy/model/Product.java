package ru.fominskiy.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

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

    @OneToMany (
            mappedBy = "product",
            orphanRemoval = true
    )
    private List<SaleBook> saleBookList;

    public Product(String title, Float cost) {
        this.title = title;
        this.cost = cost;
    }
}
