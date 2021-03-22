package web.controllers;

import dao.UserDAO;
import model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StartPageController {
    private final UserDAO userDAO;

    public StartPageController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("user/profile")
    public String profile(Model model,
                          Authentication authentication){
        model.addAttribute("user", loadUser(authentication.getName()));
        model.addAttribute("isLoggedIn", authentication != null);
        return "userProfile";
    }

    private User loadUser(String login) {
        User user = userDAO.findByLogin(login);
        return user;
    }
}
