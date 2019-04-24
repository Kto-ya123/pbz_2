package corseproject.controller;

import corseproject.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
    @PostMapping("/create")
    public String addDick(@RequestParam String text,
                          @AuthenticationPrincipal User user,
                          Model model){
        model.addAttribute("user", user);
        //model.addAttribute("message", "xor");
        return "redirect:";
    }

    @GetMapping("/create")
    public String Dick(@AuthenticationPrincipal User user,
                          Model model){
        model.addAttribute("user", user);
        //model.addAttribute("message", "xor");
        return "redirect:";
    }
}
