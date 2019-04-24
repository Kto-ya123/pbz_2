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
        && !authUser.getRoles().contains(Role.ADMIN)){
            return "redirect:/";
        }

        List<TShirt> tShirts =  tShirtRepository.findByAuthor(user);
        model.addAttribute("tShirts", tShirts);
        model.addAttribute("user", authUser);
        model.addAttribute("userpage", user);
        return "mypage";
    }
}
