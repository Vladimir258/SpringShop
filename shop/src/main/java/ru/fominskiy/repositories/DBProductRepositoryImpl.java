package ru.fominskiy.repositories;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.fominskiy.persists.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Primary
@Repository("persistentProductRepository")
public class DBProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();
    private final AtomicLong identity = new AtomicLong(0);

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("select p from Product p", Product.class).getResultList();
    }

    @Override
    public Optional<Product> findById(long id) {
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

    @Override
    public void insert(Product product) {
        long id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }
    @Transactional
    @Override
    public Product save(Product product) {
        if(product.getId() != null) {
            entityManager.merge(product);
        } else {
            entityManager.persist(product);
        }
        return product;
    }

    @Transactional
    @Override
    public void delete(long id) {
        entityManager.createQuery("delete from Product p where p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
