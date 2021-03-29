package web.controllers;

import dao.TrystDAO;
import model.Tryst;
import model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TrystController {
    private final TrystDAO trystDAO;

    public TrystController(TrystDAO trystDAO) {
        this.trystDAO = trystDAO;
    }

    @GetMapping("/user/trysts/{id}")
    public String showInfo(Model model,
                           @PathVariable("id") int id,
                           Authentication authentication) {
        Tryst tryst = trystDAO.getOne(id);
        model.addAttribute("dateT", tryst.getDate());

        User candidate;
        if (tryst.getInviter().getLogin().equals(authentication.getName())) {
            candidate = tryst.getInviter();
        } else {
            candidate = tryst.getInvitee();
        }

        model.addAttribute("candidate", candidate);
        return "infoTryst";
    }
}
