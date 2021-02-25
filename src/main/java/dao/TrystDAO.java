package dao;

import model.Tryst;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class TrystDAO {
    private final EntityManager manager;

    public TrystDAO(EntityManager manager) {
        this.manager = manager;
    }

    public long trystCount(String user) {
        try {
            return manager.createQuery("select count(t.id) from Tryst t where t.inviter.login =: user OR t.invitee.login =: user",
                    Long.class)
                    .setParameter("user", user)
                    .setParameter("user", user)
                    .getSingleResult();
        } catch (NoResultException e) {
            return 0;
        }
    }

    public List<Tryst> findArchive (String user) {
        return manager.createQuery("from Tryst t where (t.inviter.login =: user OR t.invitee.login =: user) AND t.isFinished = true",
                Tryst.class)
                .setParameter("user", user)
                .setParameter("user", user)
                .getResultList();
    }
}
