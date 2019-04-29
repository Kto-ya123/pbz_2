package corseproject.controller;

import corseproject.domain.Comment;
import corseproject.domain.TShirt;
import corseproject.repos.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comment/getcomment/{tShirt}")
    public String getComment(@PathVariable TShirt tShirt, Model model){
        List<Comment> comments = commentRepository.findByTShirt(tShirt);
        model.addAttribute("comments", comments);
        return "comments";
    }

}
