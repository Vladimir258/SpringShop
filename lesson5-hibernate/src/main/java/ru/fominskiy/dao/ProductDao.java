package ru.fominskiy.dao;

import lombok.AllArgsConstructor;
import ru.fominskiy.model.Product;

import javax.persistence.EntityManager;
import java.util.List;

@AllArgsConstructor
public class ProductDao {

    EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Product findById(Long id) {
        Product product = entityManager.find(Product.class, id);
        return product;
    }

    public List<Product> findAll() {
        return entityManager.createQuery("select p from Product p").getResultList();
    }

    public void saveOrUpdate(Product product) {
        if(product.getId() == null) {
            entityManager.persist(product);
        } else {
            entityManager.merge(product);
        }
    }

    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }
}
