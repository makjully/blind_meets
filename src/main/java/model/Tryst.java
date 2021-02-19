package model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tryst {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private Date date;

    @OneToOne
    private User user1;

    @OneToOne
    private User user2;

    public Tryst() {

    }

    public Tryst(Date date, User user1, User user2) {
        this.date = date;
        this.user1 = user1;
        this.user2 = user2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
}
