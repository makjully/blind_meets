package dao;

import model.Interest;
import model.InterestGeneral;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

public class InterestDAOTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private InterestDAO interestDAO;

    @Before
    public void configure() {
        factory = Persistence.createEntityManagerFactory(
                "TestPersistenceUnit"
        );
        manager = factory.createEntityManager();
        interestDAO = new InterestDAO(manager);

        User user1 = new User("tom123", "123");
        User user2 = new User("kate", "321");
        Interest interest1 = new Interest(InterestGeneral.IT, user1);
        Interest interest2 = new Interest(InterestGeneral.PHOTOGRAPHY, user1);
        Interest interest3 = new Interest(InterestGeneral.TRAVELING, user2);
        Interest interest4 = new Interest(InterestGeneral.IT, user2);

        manager.getTransaction().begin();
        manager.persist(user1);
        manager.persist(user2);
        manager.persist(interest1);
        manager.persist(interest2);
        manager.persist(interest3);
        manager.persist(interest4);
        manager.getTransaction().commit();
    }

    @After
    public void cleanup() {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void findUsersByInterest() {
        List<String> foundIT = interestDAO.findUsersByInterest("IT".toLowerCase());
        assertEquals(2, foundIT.size());

        List<String> foundPH = interestDAO.findUsersByInterest("PHOTOGRAPHY".toLowerCase());
        assertEquals("tom123", foundPH.get(0));
    }
}