package dao;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAO {
    private final EntityManager manager;

    @Autowired
    public UserDAO(EntityManager manager) {
        this.manager = manager;
    }

    public List<User> findByName(String name) {
        return manager.createQuery("from User where name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<User> findByCity(String city) {
            return manager.createQuery("from User where city = :city", User.class)
                    .setParameter("city", city)
                    .getResultList();
    }

    public List<User> findByAge(int age) {
        return manager.createQuery("from User where age = :age", User.class)
                .setParameter("age", age)
                .getResultList();
    }
}
