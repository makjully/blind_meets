package dto;

import model.User;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class ConvertingService {

    public static User transferFromDTOtoUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setAge(user.getDateOfBirth());
        user.setCity(userDTO.getCity());
        user.setGender(userDTO.getGender());
        user.setInterests(userDTO.getUserInterests());

        return user;
    }

    public static UserDTO transferFromUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setName(user.getName());
        userDTO.setDateOfBirth(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(user.getDateOfBirth()));
        userDTO.setCity(user.getCity());
        userDTO.setGender(user.getGender());

        return userDTO;
    }
}
