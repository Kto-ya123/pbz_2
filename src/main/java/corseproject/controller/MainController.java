package corseproject.controller;

import corseproject.domain.Comment;
import corseproject.domain.TShirt;
import corseproject.domain.Tag;
import corseproject.domain.User;
import corseproject.repos.CommentRepository;
import corseproject.repos.TShirtRepository;
import corseproject.repos.TagRepository;
import corseproject.repos.UserRepository;
import corseproject.service.TShirtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TShirtService tShirtService;
    @Autowired
    private TShirtRepository tShirtRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public  String greeting(@AuthenticationPrincipal User authUser, Model model)  {
        List<TShirt> tShirts = tShirtService.getNew();
        List<TShirt> popularShirts = tShirtService.getPopular();
        Iterable<Tag> tags = tagRepository.findAll();
        tags.forEach(tag -> tag.setTagName(tag.getTagName().substring(1)));
        model.addAttribute("tShirts", tShirts);
        model.addAttribute("popular", popularShirts);
        model.addAttribute("user", authUser);
        model.addAttribute("tags", tags);
        return "greeting";

    }
}