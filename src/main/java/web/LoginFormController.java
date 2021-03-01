package web;

import model.Gender;
import model.Interest;
import model.InterestGeneral;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class LoginFormController {
    @PostMapping("/login")
    public String login(Model model, @RequestParam String login, String password) {
        model.addAttribute("title", "Blind_Meets");
        model.addAttribute("user", loadUser(login, password));
        return "login";
    }

    private User loadUser (String login, String password) {
        User user = new User(login, password);
        user.setName("John");
        user.setCity("Amsterdam");
        user.setDateOfBirth("10/07/1995");
        user.setAge(user.getDateOfBirth());
        user.setGender(Gender.MALE);
        user.setInterests(Arrays.asList(
                new Interest(InterestGeneral.IT, user),
                new Interest(InterestGeneral.MUSIC, user),
                new Interest(InterestGeneral.PHOTOGRAPHY, user)));
        return user;
    }
}
