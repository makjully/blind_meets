package model;

import converters.UserGenderConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private int id;

    @Column(unique = true, nullable = false, length = 20)
    @EqualsAndHashCode.Include
    private String login;

    @Column(nullable = false, length = 100)
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
    private Gender gender = Gender.MALE;

    @OneToMany(mappedBy = "user")
    private List<Interest> interests;

    @OneToMany(mappedBy = "inviter")
    private List<Tryst> initiatedTrysts;

    @OneToMany(mappedBy = "invitee")
    private List<Tryst> acceptedTrysts;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setDateOfBirth(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dateOfBirth = LocalDate.parse(date, formatter);
    }

    public void setInterests(String[] interests) {
        this.interests = new ArrayList<>();
        Arrays.asList(interests).forEach(interest -> this.interests.add(
                new Interest(InterestGeneral.valueOf(interest.toUpperCase()), this)));
    }

    public void setAge(LocalDate dateOfBirth) {
        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
