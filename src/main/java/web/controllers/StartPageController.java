package web.controllers;

import dao.UserDAO;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.UserSession;

@Controller
@SessionAttributes("user-session")
public class StartPageController {
    private final UserDAO userDAO;

    public StartPageController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/profile")
    public String profile(@ModelAttribute("user-session") UserSession session,
                          Model model){
        model.addAttribute("user", loadUser(session.getUserLogin()));
        return "userProfile";
    }

    private User loadUser(String login) {
        User user = userDAO.findUserByLogin(login);
        return user;
    }
}
