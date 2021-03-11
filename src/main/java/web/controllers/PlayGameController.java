package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import web.UserSession;

@Controller
@SessionAttributes("user-session")
public class PlayGameController {
    @GetMapping("/play")
    public String playgame(@ModelAttribute("user-session") UserSession session,
                          Model model){
        return "playgame";
    }
}
