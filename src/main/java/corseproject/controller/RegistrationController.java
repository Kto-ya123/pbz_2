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
import java.util.Map;


@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @GetMapping("/reg")
    public String reg(){
        return "reg";
    }

    @PostMapping("/reg")
    public String addUse(@RequestParam() String password, @RequestParam String passwordrepeat,
                          @RequestParam() String email,  User user, Model model){
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if(userFromDb != null){
            model.addAttribute("errormessage", "User exists!");
            return"reg";
        }
        if(!password.equals(passwordrepeat)){
            model.addAttribute("errormessage", "passwords don't equal");
            return "reg";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/#login_form";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam() String password, @RequestParam String passwordrepeat,
                          @RequestParam() String email,  User user, Model model){
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if(userFromDb != null){
            model.addAttribute("errormessage", "User exists!");
            return"registration";
        }
        if(password != passwordrepeat){
            model.addAttribute("errormessage", "passwords don't equal");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/#login_form";
    }
    /*@GetMapping("/error")
    public String error(){
        return "redirect:/";
    }*/
}
