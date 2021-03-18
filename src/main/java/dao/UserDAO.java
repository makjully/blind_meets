package dao;

import model.Gender;
import model.Interest;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    @Query
    public List<User> findByGender(Gender gender);

    @Query
    public List<User> findByCity(String city);

    @Query
    public List<User> findByAge(int age);

    @Query
    public User findByLoginAndPassword(String login, String password);

    @Query
    public User findByLogin(String login);

    @Transactional
    public default User saveUser(User user) {
        return save(user);
    }
}
