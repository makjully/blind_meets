package web.controllers;

import dao.UserDAO;
import dto.UserDTO;
import model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ProfileController {
    private final UserDAO userDAO;

    public ProfileController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/user/profile")
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

    @GetMapping("/user/profile/update")
    public String updateUserProfile(Model model,
                                    @ModelAttribute("updatedUser") UserDTO newUser,
                                    BindingResult bindingResult) {
        return null;
    }

    @PostMapping("/user/profile/update")
    public String updatedProfile(Model model,
                                 @ModelAttribute("updatedUser") @Valid UserDTO newUser,
                                 BindingResult bindingResult) {
        return "redirect:/userProfile";
    }
}
