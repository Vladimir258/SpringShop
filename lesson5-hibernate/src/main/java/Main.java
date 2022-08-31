import org.hibernate.cfg.Configuration;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =   new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

    }
}
