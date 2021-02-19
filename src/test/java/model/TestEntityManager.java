package model;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class TestEntityManager {
    private EntityManagerFactory factory;
    private EntityManager manager;

    @Test
    public void smokeTest() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            User user1 = new User("tom123", "123", "Tom", 20, "male", "London");
            manager.persist(user1);
            manager.getTransaction().commit();

            manager.getTransaction().begin();
            User user2 = new User("kate123", "123", "Kate", 24, "female", "London");
            manager.persist(user2);
            manager.getTransaction().commit();

            manager.getTransaction().begin();
            Message message = new Message("Hello", user1, user2);
            manager.persist(message);
            manager.getTransaction().commit();

            manager.getTransaction().begin();
            Tryst tryst = new Tryst(new Date(), user1, user2);
            manager.persist(tryst);
            manager.getTransaction().commit();

        } finally {
            manager.close();
            factory.close();
        }
    }
}
