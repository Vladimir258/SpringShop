package ru.fominskiy.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.fominskiy.entities.Product;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    @Query(value = "select * from products p " +
            "where (:titleFilter is null or p.title like :titleFilter)" +
            "and (p.cost < :costMaxFilter) and (p.cost > :costMinFilter)",
            nativeQuery = true)
    List<Product> productsByTitleAndCost(String titleFilter, BigDecimal costMinFilter, BigDecimal costMaxFilter);
}
