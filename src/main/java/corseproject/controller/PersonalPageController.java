package corseproject.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import corseproject.domain.Comment;
import corseproject.domain.Message;
import corseproject.domain.TShirt;
import corseproject.domain.User;
import corseproject.repos.CommentRepository;
import corseproject.repos.TShirtRepository;
import corseproject.repos.UserRepository;
import corseproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/mypage")
public class PersonalPageController {
    //@Autowired
    //private UserRepository userRepository;
    @Autowired
    private TShirtRepository tShirtRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Value("${upload.path}")
    private String uploadPath;
    @Value("${cloudinary.path}")
    private String cloudinaryPath;
    @GetMapping("{tShirt}")
    public String style(@PathVariable TShirt tShirt, Model model){
        List<Comment> comments = commentRepository.findByTShirt(tShirt);
        model.addAttribute("tShirt", tShirt);
        model.addAttribute("comments", comments);
        return "product";
    }

    @PostMapping("add")
    public String addComment(
            @RequestParam("tShirtId") TShirt tShirt,
            @RequestParam String text,
            @AuthenticationPrincipal User user){

        Comment comment = new Comment();
        comment.settShirt(tShirt);
        comment.setAuthor(user);
        comment.setMessage(text);
        commentRepository.save(comment);

        return "redirect:/mypage/"+tShirt.getId();

    }

    @GetMapping()
    public String personalPage(Model model, @AuthenticationPrincipal User user){
        if(user == null){
            return "redirect:/";
        }

        List<TShirt> tShirts =  tShirtRepository.findByAuthor(user);
        model.addAttribute("tShirts", tShirts);
        return "personal";
    }
    @PostMapping()
    public String addStyle(@AuthenticationPrincipal User user,
                            @RequestParam("file") MultipartFile file,
                            Model model) throws IOException {
        if (file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            file.transferTo(new File(uploadPath + "/" + file.getOriginalFilename()));
            File uploadFile = new File(uploadPath + "/" + file.getOriginalFilename());

            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "itr",
                    "api_key", "224226883725776",
                    "api_secret", "b1t0r9MrMI4YHq5oeCQs3avCsq4"));
            Map uploadRezult = cloudinary.uploader().upload(uploadFile, ObjectUtils.emptyMap());

            TShirt tShirt = new TShirt();
            tShirt.setAuthor(user);
            tShirt.setUrlShirt(uploadRezult.get("secure_url").toString());

            try {
                tShirtRepository.save(tShirt);
            }catch (Exception e){
                model.addAttribute("message", e.getMessage());
            }
            List<TShirt> tShirts =  tShirtRepository.findByAuthor(user);
            model.addAttribute("tShirts", tShirts);
        }
        return "personal";
    }
}
