package corseproject.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import corseproject.domain.Comment;
import corseproject.domain.TShirt;
import corseproject.domain.User;
import corseproject.repos.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/TShirts")
public class TShirtController {
    @Autowired
    private CommentRepository commentRepository;
    @Value("${upload.path}")
    private String uploadPath;

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
                           Model model){
        return "addStyle";
    }
    @PostMapping("/add")
    public String addDick(@RequestParam String svg,
                          /*@RequestParam File file,*/
                          @AuthenticationPrincipal User user,
                          Model model) throws IOException {

        FileWriter writer = new FileWriter(uploadPath + "/file.png", false);
        writer.append(svg);
        writer.flush();

            //file.transferTo(new File(uploadPath + "/file"));
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name","itr",
                    "api_key","224226883725776",
                    "api_secret", "b1t0r9MrMI4YHq5oeCQs3avCsq4"));
            //Map uploadRezult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            //Map uploadRezult2 = cloudinary.uploader().upload("localhost:8080/img/image.png", ObjectUtils.emptyMap());
            //uploadRezult.toString();
            Map uploadRezult = cloudinary.uploader().upload(uploadPath + "/file.png", ObjectUtils.emptyMap());
            model.addAttribute("string", uploadRezult.get("secure_url").toString());

        model.addAttribute("user", user);
        //model.addAttribute("messages", svg);
        return "add";
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
