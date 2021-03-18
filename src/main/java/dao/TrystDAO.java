package dao;

import model.Tryst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrystDAO extends JpaRepository<Tryst, Integer> {

    @Query("select count(t.id) from Tryst t where t.inviter.login = :user OR t.invitee.login = :user")
    public long findTrystsCount(@Param("user") String user);

    @Query("from Tryst t where (t.inviter.login = :user OR t.invitee.login = :user) AND t.isFinished = true")
    public List<Tryst> findAllByFinishedIsTrue(@Param("user") String user);
}
