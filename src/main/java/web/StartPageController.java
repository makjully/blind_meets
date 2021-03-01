package web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartPageController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("title", "Blind_Meets");
        return "index";
    }
}
