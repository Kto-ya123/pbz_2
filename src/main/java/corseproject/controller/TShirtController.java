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

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/TShirts")
public class TShirtController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TShirtRepository tShirtRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping()
    public String allStyle(@AuthenticationPrincipal User authUser,
                             Model model){

        Iterable<TShirt> tShirts = tShirtRepository.findAll();

        model.addAttribute("user", authUser);
        model.addAttribute("tShirts", tShirts);

        return "allstyles";
    }

    @GetMapping("/{tShirt}")
    public String style(@AuthenticationPrincipal User authUser,
                        @PathVariable TShirt tShirt, Model model){
        List<Comment> comments = commentRepository.findByTShirt(tShirt);
        model.addAttribute("tShirt", tShirt);
        model.addAttribute("comments", comments);
        model.addAttribute("user", authUser);
        return "product";
    }

    @GetMapping("/add")
    public String addStyle(@AuthenticationPrincipal User authUser,
                           @RequestParam String username,
                           Model model){
        User user = userRepository.findByUsername(username);
        if(!authUser.getUsername().equals(user.getUsername())
                && !authUser.getRoles().contains(Role.ADMIN)){
            return "redirect:/";
        }
        model.addAttribute("user", authUser);
        model.addAttribute("userpage", user);

        return "addStyle";
    }

    @PostMapping("/add")
    public String addShirt(@RequestParam String svg,
                          @AuthenticationPrincipal User authUser,
                          @RequestParam String username,
                          Model model) throws IOException {
        User user = userRepository.findByUsername(username);
        FileWriter writer = new FileWriter(uploadPath + "/file.png", false);
        writer.append(svg);
        writer.flush();

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name","itr",
                "api_key","224226883725776",
                "api_secret", "b1t0r9MrMI4YHq5oeCQs3avCsq4"));
        Map uploadRezult = cloudinary.uploader().upload(uploadPath + "/file.png", ObjectUtils.emptyMap());
        String pathSVG = uploadRezult.get("secure_url").toString();
        String pathPNG = pathSVG.substring(0, pathSVG.length()-3)+"png";
        TShirt tShirt = new TShirt();
        tShirt.setUrlShirt(pathPNG);
        tShirt.setAuthor(user);
        tShirtRepository.save(tShirt);

        model.addAttribute("user", authUser);

        return "redirect:/"+user.getUsername();
    }

    @PostMapping("addComment")
    public String addComment(
            @RequestParam("tShirtId") TShirt tShirt,
            @RequestParam String text,
            @AuthenticationPrincipal User user){

        Comment comment = new Comment();
        comment.settShirt(tShirt);
        comment.setAuthor(user);
        comment.setMessage(text);
        commentRepository.save(comment);

        return "redirect:/TShirts/"+tShirt.getId();

    }
}
