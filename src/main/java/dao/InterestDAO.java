package dao;

import model.Interest;
import model.InterestGeneral;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class InterestDAO {
    private EntityManager manager;

    @Autowired
    public InterestDAO(EntityManager manager) {
        this.manager = manager;
    }

    public List<String> findUsersByInterest(InterestGeneral interest) {
        return manager.createQuery("select i.user.login from Interest i where i.interest like :interest", String.class)
                .setParameter("interest", interest)
                .getResultList();
    }

    public Interest addInterest(Interest interest) {
        manager.persist(interest);

        return interest;
    }
}