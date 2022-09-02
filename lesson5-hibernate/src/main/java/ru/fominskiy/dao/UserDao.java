package ru.fominskiy.dao;

import lombok.AllArgsConstructor;
import ru.fominskiy.entity.User;

import javax.persistence.*;
import java.util.*;
import java.util.function.*;

@AllArgsConstructor
public class UserDao {

    EntityManagerFactory emFactory;

    public Optional<User> findById(Long id) {
       return executeForEntityManager(entityManager ->
               Optional.ofNullable(entityManager
                       .find(User.class, id)));
    }

    public List<User> findAll() {
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("findAllUsers", User.class)
                        .getResultList());
    }

    public long count() {
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("countAllUsers", Long.class)
                        .getSingleResult());
    }

    public void saveOrUpdate(User user) {
       executeInTransaction(entityManager -> {
           if(user.getId() == null) {
               entityManager.persist(user);
           } else {
               entityManager.merge(user);
           }
       });
    }

    public void deleteById(long id) {
        executeInTransaction(entityManager -> entityManager
                .createNamedQuery("deleteUserById")
                    .setParameter("id", id)
                    .executeUpdate());
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            em.close();
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
