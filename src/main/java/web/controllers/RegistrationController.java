package web.controllers;

import dao.InterestDAO;
import dao.UserDAO;
import model.Gender;
import model.InterestGeneral;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;

@Controller
public class RegistrationController {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private InterestDAO interestDAO;

    @Autowired
    private EntityManager manager;

    @GetMapping("/register")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        model.addAttribute("interests", InterestGeneral.values());
        model.addAttribute("extraValue", InterestGeneral.NONE);
        model.addAttribute("female", Gender.FEMALE);
        model.addAttribute("male", Gender.MALE);
        return "registration";
    }

    @PostMapping("/register")
    public String create(@ModelAttribute("newUser") User user,
                         @RequestParam("userInterests") String[] interests) {
        user.setInterests(interests);
        user.setAge(user.getDateOfBirth());
        manager.getTransaction().begin();
        userDAO.saveUser(user);
        user.getInterests().forEach(userInterest -> interestDAO.addInterest(userInterest));
        manager.getTransaction().commit();
        return "redirect:/login";
    }
}
