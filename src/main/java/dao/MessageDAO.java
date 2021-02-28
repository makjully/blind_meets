package dao;

import model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository
public class MessageDAO {
    private final EntityManager manager;

    @Autowired
    public MessageDAO( EntityManager manager) {
        this.manager = manager;
    }

    public List<Message> findAllSentMessages(String login) {
        return manager.createQuery("from Message m where m.sender.login = :login", Message.class)
                .setParameter("login", login)
                .getResultList();
    }

    public List<Message> findAllReceivedMessages(String login) {
        return manager.createQuery("from Message m where m.receiver.login = :login", Message.class)
                .setParameter("login", login)
                .getResultList();
    }

    public List<Message> findAllMessagesBeforeDate(String login, Date date) {
        return manager.createQuery("from Message m where m.sender.login = :login OR m.receiver.login = :login AND m.date >= :date", Message.class)
                .setParameter("login", login)
                .setParameter("login", login)
                .setParameter("date", date)
                .getResultList();
    }
}
