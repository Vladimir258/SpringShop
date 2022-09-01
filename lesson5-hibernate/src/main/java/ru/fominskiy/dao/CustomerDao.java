package ru.fominskiy.dao;

import lombok.AllArgsConstructor;
import ru.fominskiy.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@AllArgsConstructor
public class CustomerDao {

    EntityManagerFactory emFactory;

    public Optional<Customer> findById(Long id) {
        return executeForEntityManager(entityManager ->
                Optional.ofNullable(entityManager.find(Customer.class, id)));
    }

    public List<Customer> findAll() {
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("findAllCustomers", Customer.class).getResultList());
    }

    public void saveOrUpdate(Customer customer) {
        executeInTransaction(entityManager -> {
            if(customer.getId() == null) {
                entityManager.persist(customer);
            } else {
                entityManager.merge(customer);
            }
        });
    }

    public void deleteById(long id) {
        executeInTransaction(entityManager -> entityManager.createNamedQuery("deleteCustomerById")
                .setParameter("id", id)
                .executeUpdate());
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager entityManager = emFactory.createEntityManager();
        try {
            return function.apply(entityManager);
        } finally {
            entityManager.close();
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager entityManager = emFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            consumer.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
