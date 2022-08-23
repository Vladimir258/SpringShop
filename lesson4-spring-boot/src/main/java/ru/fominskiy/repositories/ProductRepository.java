package ru.fominskiy.repositories;

import ru.fominskiy.persists.Product;

import java.util.List;

public interface ProductRepository {
    public List<Product> findAll();
    public Product findById(long id);
    public void insert(Product product);
    public Product save(Product product);
    public void delete(long id);
}
