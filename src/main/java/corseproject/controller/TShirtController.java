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
import corseproject.service.TShirtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
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
    @Autowired
    private TShirtService tShirtService;

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

        return "create";
    }

    @PostMapping("/add")
    public String addShirt(@AuthenticationPrincipal User authUser,
                          @RequestParam String svg,
                          @RequestParam String username,
                          @RequestParam String sex,
                          @RequestParam String nameProduct,
                          @RequestParam String topic,
                          @RequestParam String discription,
                          Model model) throws IOException {
        boolean addition =  tShirtService.addTShirt(username, svg, sex, nameProduct, topic, discription);

        if(addition == true) {
            return "redirect:/" + username;
        }else {
            model.addAttribute("message", "Check entered data");
            return "redirect:/TShirts/add";
        }
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
