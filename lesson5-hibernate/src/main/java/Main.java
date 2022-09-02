import org.hibernate.cfg.Configuration;
import ru.fominskiy.dao.UserDao;
import ru.fominskiy.entity.User;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        UserDao userDao = new UserDao(entityManagerFactory);
        User user;

        userDao.saveOrUpdate(new User("User1", "mail@one", "pass1"));
        userDao.saveOrUpdate(new User("User2", "mail@two", "pass2"));
        userDao.saveOrUpdate(new User("User3", "mail@three", "pass3"));

        user = userDao.findById(3L).get();
        System.out.println(user.getUsername());

        List<User> usersList = userDao.findAll();
        for (User u: usersList) {
            System.out.println(u.getUsername());
        }

        user = userDao.findById(1L).get();
        user.setUsername("NewUser");
        userDao.saveOrUpdate(user);
        userDao.deleteById(2L);

        usersList = userDao.findAll();
        for (User p: usersList) {
            System.out.println(p.getUsername());
        }

        entityManagerFactory.close();
    }
}
