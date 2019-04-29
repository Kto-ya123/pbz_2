package corseproject.controller;

import corseproject.domain.Role;
import corseproject.domain.User;
import corseproject.repos.UserRepository;
import corseproject.service.MailSender;
import corseproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String reg(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUse(@RequestParam String passwordrepeat, User user, Model model){

        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb != null){
            model.addAttribute("errormessage", "User exists!");
            return "registration";
        }
        User userFromMail = userRepository.findByEmail(user.getEmail());
        if(userFromMail != null){
            model.addAttribute("errormessage", "mail exists");
            return "registration";
        }
        if(!user.getPassword().equals(passwordrepeat)){
            model.addAttribute("errormessage", "passwords don't equal");
            return "registration";
        }
        if(!userService.addUser(user)) {
            model.addAttribute("errormessage", "check entered data");
            return "registration";
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
