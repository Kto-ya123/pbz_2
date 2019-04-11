package corseproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String main(Map<String,Object> model){
        Iterable<User> users = userRepository.findAll();
        model.put("message", users);
        return "main";
    }
    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model){
        User user = new User();
        user.setText(text);
        user.setTag(tag);

        userRepository.save(user);

        Iterable<User> users = userRepository.findAll();
        model.put("messages", users);
        return "main";

    }

    @GetMapping("/greeting")
    public  String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model){
        model.put("name", name);
        return "greeting";
    }

}