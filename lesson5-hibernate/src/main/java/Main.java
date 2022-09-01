import org.hibernate.cfg.Configuration;
import ru.fominskiy.dao.ProductDao;
import ru.fominskiy.model.Product;
import ru.fominskiy.model.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        ProductDao productDao = new ProductDao(entityManagerFactory.createEntityManager());

        productDao.getEntityManager().getTransaction().begin();
        productDao.saveOrUpdate(new Product("Product1", 100F));
        productDao.saveOrUpdate(new Product("Product2", 200F));
        productDao.saveOrUpdate(new Product("Product3", 300F));
        productDao.getEntityManager().getTransaction().commit();

        Product product = productDao.findById(3L);
        System.out.println(product.getTitle());

        List<Product> productsList = productDao.getEntityManager().createQuery("select p from Product p", Product.class).getResultList();
        for (Product p: productsList) {
            System.out.println(p.getTitle());
        }

        productDao.getEntityManager().getTransaction().begin();
        Product product1 = productDao.findById(1L);
        product1.setTitle("NewProduct");

        productDao.deleteById(2L);
        productDao.getEntityManager().getTransaction().commit();


        productsList = productDao.getEntityManager().createQuery("select p from Product p", Product.class).getResultList();
        for (Product p: productsList) {
            System.out.println(p.getTitle());
        }

        productDao.getEntityManager().close();
        entityManagerFactory.close();
    }
}
