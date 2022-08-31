import org.hibernate.cfg.Configuration;
import ru.fominskiy.model.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // INSERT
//        entityManager.getTransaction().begin();
//        entityManager.persist(new User("User1", "1@a.com", "pass1"));
//        entityManager.persist(new User("User2", "2@a.com", "pass2"));
//        entityManager.persist(new User("User3", "3@a.com", "pass3"));
//        entityManager.getTransaction().commit();

        // SELECT
//        User user = entityManager.find(User.class, 1L);
//        System.out.println(user.getUsername());

        // JPQL, HQL
                // List<User> users = entityManager.createQuery("select u from User u", User.class).getResultList();
//        List<User> users = entityManager.createQuery("select u from User u where u.id in (1, 2)", User.class).getResultList();
//        for (User u: users) {
//            System.out.println(u.getUsername());
//        }

        // UPDATE v1
//        entityManager.getTransaction().begin();
//        User user = entityManager.find(User.class, 1L);
//        user.setUsername("new Username");
//        entityManager.getTransaction().commit();
//        entityManager.close();

        // UPDATE v2
//        entityManager.getTransaction().begin();
//        User user = new User("User2Second", "2@a.com", "pass2");
//        user.setId(2L);
//        entityManager.merge(user);
//        entityManager.getTransaction().commit();
//        entityManager.close();

        // DELETE
//        entityManager.getTransaction().begin();
//        // 1 способ JPQL
//        //entityManager.createQuery("delete from User u where u.id = 3").executeUpdate();
//
//        // 2 способ
//        User user = entityManager.find(User.class, 2L);
//        entityManager.remove(user);
//
//        entityManager.getTransaction().commit();
//        entityManager.close();


        entityManagerFactory.close();
    }
}
