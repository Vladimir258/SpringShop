package ru.fominskiy.services;

import lombok.AllArgsConstructor;
import javax.persistence.*;

@AllArgsConstructor
public class SaleService {
    EntityManagerFactory emFactory;

    public void customerProducts(Long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("SELECT c " +
                        "FROM Customer c " +
                        "join c.saleBookList s " +
                        "join s.product p " +
                        "WHERE c.id = :customerId")
                .setParameter("customerId", id)
                .getResultList();
        em.getTransaction().commit();
        em.close();
    }

    public void productsCustomers (Long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("SELECT p " +
                        "FROM Product p " +
                        "join p.saleBookList s " +
                        "join s.customer c " +
                        "WHERE p.id = :productId")
                .setParameter("productId", id)
                .getResultList();
        em.getTransaction().commit();
        em.close();
    }

    public void detailBuy (Long customerId, Long productId) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("SELECT s " +
                        "FROM SaleBook s " +
                        "join s.customer c " +
                        "join s.product p " +
                        "WHERE p.id = :productId AND c.id = :customerId " )
                .setParameter("productId", productId)
                .setParameter("customerId", customerId)
                .getResultList();
        em.getTransaction().commit();
        em.close();
    }
}
