package corseproject.controller;

import com.cloudinary.utils.ObjectUtils;
import corseproject.domain.Message;
import corseproject.domain.Tag;
import corseproject.domain.Topic;
import corseproject.domain.User;
import corseproject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TShirtRepository tShirtRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter,  Model model){
        Iterable<Message> messages = messageRepository.findAll();


        if(filter != null && !filter.isEmpty()){
            //messages = messageRepository.findByTag(filter);
            User user = userRepository.findByUsername(filter);
            messages = messageRepository.findByAuthor(user);
        }else{
            messages = messageRepository.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model,
            @RequestParam("file") MultipartFile file) throws IOException {

        Message message = new Message();
        if(file != null){
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String rezultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + rezultFilename));
            File uploadFile = new File(uploadPath + "/" + "image.png");
            message.setFilename(rezultFilename);

            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name","itr",
                    "api_key","224226883725776",
                    "api_secret", "b1t0r9MrMI4YHq5oeCQs3avCsq4"));
            Map uploadRezult = cloudinary.uploader().upload(uploadFile, ObjectUtils.emptyMap());
            model.put("string", uploadRezult.get("secure_url").toString());


        }
        message.setText(text);
        message.setTag(tag);
        message.setAuthor(user);

        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "main";
    }

    @GetMapping("/")
    public  String greeting(Model model,
                            @AuthenticationPrincipal User user){

        if(user != null){
            model.addAttribute("user", user);
        }
        return "greeting";
    }
}