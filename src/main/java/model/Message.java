package model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 4000)
    private String text;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Message() {

    }

    public Message(String text, User sender, User receiver) {
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
        date = new Date();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
