package corseproject.controller;

import corseproject.domain.Comment;
import corseproject.domain.TShirt;
import corseproject.domain.User;
import corseproject.repos.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/TShirts")
public class TShirtController {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/{tShirt}")
    public String style(@AuthenticationPrincipal User authUser,
                        @PathVariable TShirt tShirt, Model model){
        List<Comment> comments = commentRepository.findByTShirt(tShirt);
        model.addAttribute("tShirt", tShirt);
        model.addAttribute("comments", comments);
        model.addAttribute("user", authUser);
        return "product";
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
