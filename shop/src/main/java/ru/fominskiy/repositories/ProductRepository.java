package ru.fominskiy.repositories;

import ru.fominskiy.persists.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(long id);
    void insert(Product product);
    Product save(Product product);
    void delete(long id);
}
