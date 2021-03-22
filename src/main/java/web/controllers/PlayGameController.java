package web.controllers;

import dao.SearchUsers;
import model.Gender;
import model.InterestGeneral;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PlayGameController {
    @GetMapping("user/play")
    public String playGame(Model model,
                           @ModelAttribute("search") SearchUsers search,
                           BindingResult bindingResult) {
        model.addAttribute("interests", InterestGeneral.values());
        model.addAttribute("extraValue", InterestGeneral.NONE);
        model.addAttribute("female", Gender.FEMALE);
        model.addAttribute("male", Gender.MALE);
        return "playgame";
    }

    @PostMapping("user/play")
    public String search(Model model,
                         @ModelAttribute("search") @Valid SearchUsers search,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return playGame(model, search, bindingResult);
        }

        List<User> candidates = search.findUsersByCriteria();
        model.addAttribute("candidates", candidates);
        return "playgame";
    }
}
