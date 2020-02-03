package corseproject.controller;

import corseproject.domain.Subdivision;
import corseproject.repos.SubdivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subdivision/redact")
public class RedactSubdivisionController {
    @Autowired
    private SubdivisionRepository subdivisionRepository;


    @GetMapping("/{subdivisionId}")
    public  String subdivision(Model model,
                               @PathVariable long subdivisionId)  {
        Subdivision subdivision = subdivisionRepository.findById(subdivisionId);
        model.addAttribute("subdivision", subdivision);
        return "redact";

    }

    @PostMapping("/{subvisionId}")
    public String addSubdivision(@PathVariable long subvisionId,
                                 @RequestParam String name,
                                 @RequestParam String fullName){

        Subdivision subdivision = subdivisionRepository.findById(subvisionId);
        subdivision.setName(name);
        subdivision.setFullName(fullName);
        subdivisionRepository.save(subdivision);

        return "redirect:/subdivision/redact/" + subvisionId;
    }


}
