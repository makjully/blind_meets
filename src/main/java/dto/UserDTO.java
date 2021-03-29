package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Gender;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Component
@Getter
@Setter
@NoArgsConstructor
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
}
