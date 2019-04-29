package corseproject.controller;

import corseproject.domain.Role;
import corseproject.domain.User;
import corseproject.repos.UserRepository;
import corseproject.service.MailSender;
import corseproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.UUID;


@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String reg(@AuthenticationPrincipal User authUser, @RequestParam(required = false) String error, Model model){
        if(error != null) {
            model.addAttribute("errormessage", error);
        }
        model.addAttribute("user", authUser);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUse(@RequestParam String passwordrepeat, User user){
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb != null){
            String message = "User exists";
            return "redirect:/registration?error="+message;
        }
        User userFromMail = userRepository.findByEmail(user.getEmail());
        if(userFromMail != null){
            String message = "mail exists";
            return "redirect:/registration?error="+message;
        }
        if(!user.getPassword().equals(passwordrepeat)){
            String message = "passwords don't equal";
            return "redirect:/registration?error="+ message;
        }

        if(!userService.addUser(user)) {
            String message = "check entered data";
            return "redirect:/registration?error=" + message;
        }

        return "redirect:/#login_form";
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code){
        userService.activateUser(code);
        return "redirect:/#login_form";
    }

    @GetMapping("/error")
    public String error(){
        return "redirect:/";
    }
}
