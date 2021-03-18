package dto;

import model.User;
import org.springframework.stereotype.Service;

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
}
