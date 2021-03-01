package dao;

import model.Message;
import model.TestConfiguration;
import model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MessageDAOTest {
    @Autowired
    private EntityManager manager;

    @Autowired
    private MessageDAO messageDAO;
    private Date now = new Date();

    @Before
    public void configure() {
        User user1 = new User("tom123", "123");
        User user2 = new User("kate", "321");

        Message message1 = new Message("Hello!", user1, user2);
        Message message2 = new Message("Hi!", user2, user1);
        Message message3 = new Message("How are you?", user1, user2);
        Message message4 = new Message("Good", user2, user1);
        Message message5 = new Message("And you?", user2, user1);

        manager.getTransaction().begin();
        manager.persist(user1);
        manager.persist(user2);
        manager.persist(message1);
        manager.persist(message2);
        manager.persist(message3);
        manager.persist(message4);
        manager.persist(message5);
        manager.getTransaction().commit();
    }

    @Test
    public void findAllSentMessages() {
        List<Message> sent1 = messageDAO.findAllSentMessages("tom123");
        assertEquals(2, sent1.size());

        List<Message> sent2 = messageDAO.findAllSentMessages("kate");
        assertEquals(3, sent2.size());
    }

    @Test
    public void findAllReceivedMessages() {
        List<Message> received = messageDAO.findAllReceivedMessages("kate");
        assertEquals(2, received.size());
    }

    @Test
    public void findAllMessagesBeforeDate() {
        List<Message> messages = messageDAO.findAllMessagesBeforeDate("kate", now);
        assertEquals(5, messages.size());
    }
}