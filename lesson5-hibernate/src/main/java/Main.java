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

        ProductDao productDao = new ProductDao(entityManagerFactory);
        Product product;

        productDao.saveOrUpdate(new Product("Product1", 100F));
        productDao.saveOrUpdate(new Product("Product2", 200F));
        productDao.saveOrUpdate(new Product("Product3", 300F));

        product = productDao.findById(3L).get();
        System.out.println(product.getTitle());

        List<Product> productsList = productDao.findAll();
        for (Product p: productsList) {
            System.out.println(p.getTitle());
        }


        product = productDao.findById(1L).get();
        product.setTitle("NewProduct");
        productDao.saveOrUpdate(product);

        productDao.deleteById(2L);

        productsList = productDao.findAll();
        for (Product p: productsList) {
            System.out.println(p.getTitle());
        }

        entityManagerFactory.close();
    }
}
