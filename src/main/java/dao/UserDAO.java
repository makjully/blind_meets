package dao;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDAO {
    private EntityManager manager;

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

    public User findUserByLoginPassword(String login, String password) {
        try {
            return manager.createQuery("from User where login =:login AND password =:password", User.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User findUserByLogin(String login) {
        try {
            return manager.createQuery("from User where login =:login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User saveUser(User user) {
        manager.persist(user);

        return user;
    }
}
