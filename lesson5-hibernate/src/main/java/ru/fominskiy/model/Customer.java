package ru.fominskiy.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(name = "findAllCustomers", query = "Select c from Customer c"),
        @NamedQuery(name = "deleteCustomerById", query = "delete from Customer c where c.id = :id")
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany (mappedBy = "customer",
            orphanRemoval = true
    )
    private List<SaleBook> saleBookList;

    public Customer(String name) {
        this.name = name;
    }
}
