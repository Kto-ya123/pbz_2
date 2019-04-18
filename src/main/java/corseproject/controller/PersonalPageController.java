package corseproject.controller;

import corseproject.domain.User;
import corseproject.repos.UserRepository;
import corseproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mypage")
public class PersonalPageController {
    //@Autowired
    //private UserRepository userRepository;
    @GetMapping("{user}")
    private String personalPage(@PathVariable User user, Model model,
                                @AuthenticationPrincipal User authUser){
        //User user = userRepository.findByUsername()
        if(!user.getUsername().equals(authUser.getUsername())){
            return "redirect:/";
        }
        model.addAttribute("message", "Hello " + user.getUsername());
        return "personal";
    }
}
