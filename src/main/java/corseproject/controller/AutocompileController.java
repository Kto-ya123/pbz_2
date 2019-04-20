package corseproject.controller;

import corseproject.domain.User;
import corseproject.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/auto")
public class AutocompileController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping()
    public String backet(@AuthenticationPrincipal User authUser, Model model){
        model.addAttribute("user", authUser);
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "autocomplete";
    }
}
