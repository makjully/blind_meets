package web.controllers;

import dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginFormController {
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
