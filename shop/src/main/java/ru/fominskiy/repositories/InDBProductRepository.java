package ru.fominskiy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fominskiy.persists.Product;

import java.util.List;

@Repository
public interface InDBProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByTitleLike(String productFilter);

    @Query(value = "select * from products p where p.title like :title", nativeQuery = true)
    List<Product> productsByTitle(String title);

}
