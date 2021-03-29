package web.controllers;

import dao.SearchUsers;
import dao.TrystDAO;
import dao.UserDAO;
import model.Gender;
import model.InterestGeneral;
import model.Tryst;
import model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class PlayGameController {
    private final TrystDAO trystDAO;

    private final UserDAO userDAO;

    public PlayGameController(TrystDAO trystDAO, UserDAO userDAO) {
        this.trystDAO = trystDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("/user/play")
    public String playGame(Model model,
                           @ModelAttribute("search") SearchUsers search,
                           BindingResult bindingResult,
                           Authentication authentication) {
        model.addAttribute("interests", InterestGeneral.values());
        model.addAttribute("extraValue", InterestGeneral.NONE);
        model.addAttribute("female", Gender.FEMALE);
        model.addAttribute("male", Gender.MALE);
        return "playgame";
    }

    @PostMapping("/user/play")
    public String search(Model model,
                         @ModelAttribute("search") @Valid SearchUsers search,
                         BindingResult bindingResult,
                         Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return playGame(model, search, bindingResult, authentication);
        }

        List<User> candidates = search.findUsersByCriteria();
        model.addAttribute("candidates", candidates);
        return "playgame";
    }

    @PostMapping("/user/play/{id}")
    public String createTryst(Model model,
                              @PathVariable("id") int id,
                              @ModelAttribute("dateT") String date,
                              BindingResult bindingResult,
                              Authentication authentication) {
        User candidate = userDAO.getOne(id);
        model.addAttribute("candidateChosen", candidate);
        LocalDate trystDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (trystDate.isBefore(LocalDate.now())) {
            bindingResult.addError(new FieldError("form", "Date", "Date should be in Future"));
        }

        if (bindingResult.hasErrors()) {
            return createTryst(model, id, null, bindingResult, authentication);
        }

        User current = userDAO.findByLogin(authentication.getName());
        Tryst tryst = new Tryst(trystDate, current, candidate);
        trystDAO.saveTryst(tryst);
        userDAO.updateInviterWithTryst(current, tryst);
        userDAO.updateInviteeWithTryst(candidate, tryst);

        return "createTryst";
    }
}
