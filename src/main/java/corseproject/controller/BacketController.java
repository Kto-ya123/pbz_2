package corseproject.controller;

        import corseproject.domain.TShirt;
        import corseproject.domain.User;
        import org.springframework.security.core.annotation.AuthenticationPrincipal;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/TShirts/{tShirt}/backet")
public class BacketController {
    @GetMapping()
    public String backet(@AuthenticationPrincipal User authUser,
                         @PathVariable TShirt tShirt,  Model model){
        model.addAttribute("tShirt", tShirt);
        model.addAttribute("user", authUser);
        return "backet";
    }
}
