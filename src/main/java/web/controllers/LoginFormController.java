package web.controllers;

import dao.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.UserSession;

@Controller
@SessionAttributes("user-session")
public class LoginFormController {
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String submitLoginForm(
            @RequestParam String login,
            @RequestParam String password,
            @ModelAttribute("user-session") UserSession session,
            Model model
    ) {
        User user = userDAO.findByLoginAndPassword(login, password);

        if (user != null) {
            model.addAttribute("user", user);
            session.setUserLogin(login);
            return "redirect:/profile";
        } else {
            return "/login";
        }
    }

    @ModelAttribute("user-session")
    public UserSession createUserSession() {
        return new UserSession();
    }
}
