package model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tryst {
    @Id
    @GeneratedValue
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column
    private boolean isFinished;

    @ManyToOne
    private User inviter;

    @ManyToOne
    private User invitee;

    public Tryst() {

    }

    public Tryst(Date date, User inviter, User invitee) {
        this.date = date;
        this.inviter = inviter;
        this.invitee = invitee;
        isFinished = false;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getInviter() {
        return inviter;
    }

    public void setInviter(User inviter) {
        this.inviter = inviter;
    }

    public User getInvitee() {
        return invitee;
    }

    public void setInvitee(User invitee) {
        this.invitee = invitee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
