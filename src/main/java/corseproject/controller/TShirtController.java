package corseproject.controller;

import corseproject.domain.*;
import corseproject.repos.CommentRepository;
import corseproject.repos.TShirtRepository;
import corseproject.repos.TagRepository;
import corseproject.repos.TopicRepository;
import corseproject.service.TShirtService;
import corseproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/TShirts")
public class TShirtController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TShirtRepository tShirtRepository;
    @Autowired
    private TShirtService tShirtService;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TagRepository tagRepository;

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
        if(userService.getAccess(authUser, tShirt.getAuthor().getUsername())){
            model.addAttribute("access", 1);
        }
        model.addAttribute("tShirt", tShirt);
        model.addAttribute("comments", comments);
        model.addAttribute("user", authUser);
        return "newproduct";
    }


    @GetMapping("/add")
    public String addStyle(@AuthenticationPrincipal User authUser,
                           @RequestParam String username,
                           @RequestParam(required = false) String message,
                           Model model){
        if(!userService.getAccess(authUser, username)){
            return"redirect:/";
        }
        Iterable<Topic> topics = topicRepository.findAll();
        Iterable<Tag> tags = tagRepository.findAll();

        model.addAttribute("exists_tag", tags);
        model.addAttribute("topics", topics);
        model.addAttribute("user", authUser);
        model.addAttribute("userpage", username);
        model.addAttribute("message", message);

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
                          @RequestParam String tags,
                          Model model) throws IOException {
        boolean addition =  tShirtService.addTShirt(username, svg, sex, nameProduct, topic, discription, tags);

        if(addition == true) {
            return "redirect:/" + username;
        }else {
            String message = "Check your entered data";
            return "redirect:/TShirts/add?username="+username + "&message="+message;
        }
    }

    @PostMapping("/{tShirt}/delete")
    public String deleteStyle(@AuthenticationPrincipal User authUser,
                              @PathVariable TShirt tShirt){
        if(userService.getAccess(authUser, tShirt.getAuthor().getUsername())){
            tShirtRepository.delete(tShirt);
            return "redirect:/"+tShirt.getAuthor().getUsername();
        }
        return "redirect:/";
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
