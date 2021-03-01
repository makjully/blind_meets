package model;

import converters.UserGenderConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false, length = 20)
    private String login;

    @Column(nullable = false, length = 15)
    private String password;

    @Column(length = 20)
    private String name;

    @Column
    private LocalDate dateOfBirth;

    @Column
    private int age;

    @Column(length = 50)
    private String city;

    @Convert(converter = UserGenderConverter.class)
    private Gender gender = Gender.NONE;

    @OneToMany(mappedBy = "user")
    private List<Interest> interests;

    @OneToMany(mappedBy = "inviter")
    private List<Tryst> initiatedTrysts;

    @OneToMany(mappedBy = "invitee")
    private List<Tryst> acceptedTrysts;

    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver")
    private List<Message> receivedMessages;

    public User() {

    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        this.dateOfBirth = LocalDate.parse(date, formatter);
    }

    public List<Tryst> getInitiatedTrysts() {
        return initiatedTrysts;
    }

    public void setInitiatedTrysts(List<Tryst> initiatedTrysts) {
        this.initiatedTrysts = initiatedTrysts;
    }

    public List<Tryst> getAcceptedTrysts() {
        return acceptedTrysts;
    }

    public void setAcceptedTrysts(List<Tryst> acceptedTrysts) {
        this.acceptedTrysts = acceptedTrysts;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    public int getAge() {
        return age;
    }

    public void setAge(LocalDate dateOfBirth) {
        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
