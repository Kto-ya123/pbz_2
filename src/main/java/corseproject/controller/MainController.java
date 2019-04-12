package corseproject.controller;

import corseproject.domain.Message;
import corseproject.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/main")
    public String main(Map<String,Object> model){
        Iterable<Message> users = messageRepository.findAll();
        model.put("message", users);
        return "main";
    }
    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model){
        Message message = new Message();
        message.setText(text);
        message.setTag(tag);

        messageRepository.save(message);

        Iterable<Message> users = messageRepository.findAll();
        model.put("messages", users);
        return "main";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        List<Message> messages = messageRepository.findByTag(filter);
        model.put("messages", messages);
        return "main";
    }

    @GetMapping("/")
    public  String greeting(Map<String, Object> model){
        return "greeting";
    }


}