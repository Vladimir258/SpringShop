import org.hibernate.cfg.Configuration;
import ru.fominskiy.dao.CustomerDao;
import ru.fominskiy.dao.ProductDao;
import ru.fominskiy.services.SaleService;

import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        ProductDao productDao = new ProductDao(entityManagerFactory);
        CustomerDao customerDao = new CustomerDao(entityManagerFactory);

        SaleService saleService = new SaleService(entityManagerFactory);

        saleService.customerProducts(1L);
        saleService.productsCustomers(2L);
        saleService.detailBuy(1L,2L);

//        Product product;
//
//        productDao.saveOrUpdate(new Product("Product1", 100F));
//        productDao.saveOrUpdate(new Product("Product2", 200F));
//        productDao.saveOrUpdate(new Product("Product3", 300F));
//
//        product = productDao.findById(3L).get();
//        System.out.println(product.getTitle());
//
//        List<Product> productsList = productDao.findAll();
//        for (Product p: productsList) {
//            System.out.println(p.getTitle());
//        }
//
//
//        product = productDao.findById(1L).get();
//        product.setTitle("NewProduct");
//        productDao.saveOrUpdate(product);
//
//        productDao.deleteById(2L);
//
//        productsList = productDao.findAll();
//        for (Product p: productsList) {
//            System.out.println(p.getTitle());
//        }
//
//        entityManagerFactory.close();
    }
}
