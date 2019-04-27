package corseproject.controller;

import corseproject.domain.Role;
import corseproject.domain.TShirt;
import corseproject.domain.User;
import corseproject.repos.TShirtRepository;
import corseproject.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TShirtRepository tShirtRepository;
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @PostMapping
    public String userSave(
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user){

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();

        for(String key:form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);

        return "redirect:user";

    }
    @PostMapping("delete")
    public String userDelete(@RequestParam("userId") User user){

        List<TShirt> tShirts = tShirtRepository.findByAuthor(user);
        User admin = userRepository.findByUsername("admin");
        for (TShirt tShirt: tShirts) {
            tShirt.setAuthor(admin);
            tShirtRepository.save(tShirt);

        }
        userRepository.delete(user);
        return "redirect:";

    }

    @PostMapping("block")
    public String userBlock(@RequestParam("userId") User user){
        user.setActive(false);
        userRepository.save(user);
        return "redirect:";

    }

    @PostMapping("unblock")
    public String userUnblock(@RequestParam("userId") User user){
        user.setActivationCode(null);
        user.setActive(true);
        userRepository.save(user);
        return "redirect:";

    }

}
