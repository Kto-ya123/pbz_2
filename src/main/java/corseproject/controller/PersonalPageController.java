package corseproject.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import corseproject.domain.Comment;
import corseproject.domain.Role;
import corseproject.domain.TShirt;
import corseproject.domain.User;
import corseproject.repos.CommentRepository;
import corseproject.repos.TShirtRepository;
import corseproject.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/{username}")
public class PersonalPageController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TShirtRepository tShirtRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${cloudinary.path}")
    private String cloudinaryPath;

    @GetMapping()
    public String personalPage(Model model,
                               @AuthenticationPrincipal User authUser,
                               @PathVariable String username){
        User user = userRepository.findByUsername(username);
        if(!authUser.getUsername().equals(user.getUsername())
        && !authUser.getRoles().contains(Role.ADMIN) ){
            return "redirect:/";
        }

        List<TShirt> tShirts =  tShirtRepository.findByAuthor(user);
        model.addAttribute("tShirts", tShirts);
        model.addAttribute("user", authUser);
        return "mypage";
    }

    @PostMapping()
    public String addStyle(@AuthenticationPrincipal User authUser,
                           @PathVariable String username,
                           @RequestParam("file") MultipartFile file,
                           Model model) throws IOException {
        User user = userRepository.findByUsername(username);
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
            //Map uploadRezult = cloudinary.uploader().upload(, ObjectUtils.emptyMap());

            TShirt tShirt = new TShirt();
            tShirt.setAuthor(user);
            tShirt.setUrlShirt(uploadRezult.get("secure_url").toString());

            try {
                tShirtRepository.save(tShirt);
            }catch (Exception e){
                model.addAttribute("message", e.getMessage());
            }
        }
        return "redirect:/"+user.getUsername();
    }
}
