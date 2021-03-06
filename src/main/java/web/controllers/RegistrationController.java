package web.controllers;

import dao.InterestDAO;
import dao.UserDAO;
import dto.ConvertingService;
import dto.UserDTO;
import model.Gender;
import model.InterestGeneral;
import model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final UserDAO userDAO;

    private final InterestDAO interestDAO;

    private final PasswordEncoder encoder;

    public RegistrationController(UserDAO userDAO, InterestDAO interestDAO, PasswordEncoder encoder) {
        this.userDAO = userDAO;
        this.interestDAO = interestDAO;
        this.encoder = encoder;
    }

    @GetMapping("/register")
    public String newUser(Model model,
                          @ModelAttribute("newUser") UserDTO newUser,
                          BindingResult bindingResult) {
        model.addAttribute("interests", InterestGeneral.values());
        model.addAttribute("extraValue", InterestGeneral.NONE);
        model.addAttribute("female", Gender.FEMALE);
        model.addAttribute("male", Gender.MALE);
        return "registration";
    }

    @PostMapping("/register")
    public String create(Model model,
                         @ModelAttribute("newUser") @Valid UserDTO newUser,
                         BindingResult bindingResult) {
        User user = userDAO.findByLogin(newUser.getLogin());
        if (user != null) {
            bindingResult.addError(new FieldError("form", "login", "This login is already used by another user"));
        }

        if (bindingResult.hasErrors()) {
            return newUser(model, newUser, bindingResult);
        }

        newUser.setPassword(encoder.encode(newUser.getPassword()));

        user = ConvertingService.transferFromDTOtoUser(newUser);
        userDAO.saveUser(user);
        user.getInterests().forEach(userInterest -> interestDAO.addInterest(userInterest));
        return "redirect:/login";
    }
}
