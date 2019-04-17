package corseproject.controller;

import corseproject.domain.Role;
import corseproject.domain.User;
import corseproject.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;


@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

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
        if(!user.getPassword().equals(passwordrepeat)){
            model.addAttribute("errormessage", "passwords don't equal");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        try {
            userRepository.save(user);
        }catch (Exception e){
            model.addAttribute("errormessage", "check entered data");
            return "registration";
        }

        return "redirect:/#login_form";
    }
    /*
    @GetMapping("/error")
    public String error(){
        return "redirect:/";
    }*/
}
