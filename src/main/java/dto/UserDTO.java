package dto;

import model.Gender;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Component
public class UserDTO {
    @NotBlank(message = "Login should not be empty")
    @Size(min = 2, max = 20, message = "Login should be between 2 and 20 characters")
    private String login;

    @NotBlank(message = "Password should not be empty")
    @Size(min = 8, max = 20, message = "Password should be between 8 and 15 characters")
    private String password;

    @NotBlank(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name should contain only letters")
    private String name;

    @NotBlank(message = "Date of Birth should not be empty")
    private String dateOfBirth;

    @NotBlank(message = "City should not be empty")
    @Size(min = 2, max = 50, message = "Name  of city should be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name of city should contain only letters")
    private String city;

    private Gender gender;

    @Size(min = 1, message = "Please choose at least one interest")
    private String[] userInterests;

    public UserDTO() {
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String[] getUserInterests() {
        return userInterests;
    }

    public void setUserInterests(String[] userInterests) {
        this.userInterests = userInterests;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
