package corseproject.controller;

import corseproject.domain.*;
import corseproject.repos.*;
import corseproject.service.RatingService;
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
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private RatingService ratingService;

    @GetMapping()
    public String allStyle(@AuthenticationPrincipal User authUser,
                             @RequestParam(required = false) String inputtag,
                             @RequestParam(required = false) String topic,
                             @RequestParam(required = false) String sex,
                             Model model){

        if(sex == null){
            sex = "";
        }
        if(topic == null)
        {
            topic = "";
        }
        if(inputtag == null)
        {
            inputtag = "";
        }

        List<TShirt> tShirts = tShirtService.findWithFilter(sex, topic, inputtag);
        Iterable<Topic> topics = topicRepository.findAll();
        Iterable<Tag> tags = tagRepository.findAll();

        model.addAttribute("exists_tag", tags);
        model.addAttribute("topics", topics);
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
        int quantity = ratingService.getQuantity(tShirt);
        if(authUser != null) {
            Rating rating = ratingRepository.findByTShirtAndAuthor(tShirt, authUser);
            if(rating != null) {
                model.addAttribute("rating", rating.getValue());
            }
        }
        model.addAttribute("quantity", quantity);
        model.addAttribute("tShirt", tShirt);
        model.addAttribute("comments", comments);
        model.addAttribute("user", authUser);
        return "newproduct";
    }

    @GetMapping("/{tShirt}/rating")
    public void addRating(@PathVariable TShirt tShirt,
                            @RequestParam String value,
                            @AuthenticationPrincipal User user){
        if(user != null) {
            ratingService.addRate(user, Integer.parseInt(value), tShirt);
        }
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
