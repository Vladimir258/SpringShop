package ru.fominskiy.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.fominskiy.entities.Product;
import ru.fominskiy.entities.User;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    @Query(value = "select * from products p " +
            "where (:titleFilter is null or p.title like :titleFilter)" +
            "and (p.cost < :costMaxFilter) and (p.cost > :costMinFilter)",
            countQuery =  "select count(*) from products p " +
                    "where (:titleFilter is null or p.title like :titleFilter)" +
                    "and (p.cost < :costMaxFilter) and (p.cost > :costMinFilter)",
            nativeQuery = true)
    Page<Product> productsByTitleAndCost(String titleFilter, BigDecimal costMinFilter, BigDecimal costMaxFilter, Pageable pageable);
}
