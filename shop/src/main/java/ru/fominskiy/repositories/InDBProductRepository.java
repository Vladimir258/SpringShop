package ru.fominskiy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fominskiy.persists.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InDBProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByTitleLike(String titleFilter);

    @Query(value = "select * from products p " +
                   "where (:titleFilter is null or p.title like :titleFilter)",
                    nativeQuery = true)
    List<Product> productsByTitle(String titleFilter);

    @Query(value = "select * from products p " +
            "where (:titleFilter is null or p.title like :titleFilter)" +
            "and (p.cost < :costMaxFilter) and (p.cost > :costMinFilter)",
            nativeQuery = true)
    List<Product> productsByTitleAndCost(String titleFilter, BigDecimal costMinFilter, BigDecimal costMaxFilter);
}
