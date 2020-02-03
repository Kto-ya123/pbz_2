package corseproject.controller;

import corseproject.domain.*;
import corseproject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
public class BroadcastController {
    @Autowired
    private SubdivisionRepository subdivisionRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TechnicsRepository technicsRepository;
    @Autowired
    private FlatRepository flatRepository;
    @Autowired
    private BroadcastRepository broadcastRepository;

    @GetMapping("/broadcast/{idNumber}")
    public String broadcastTechnics(Model model,
                                    @PathVariable long idNumber){
        Technics technics = technicsRepository.findByIdNumber(idNumber);
        Iterable<Flat> flats = flatRepository.findAll();
        model.addAttribute("technics", technics);
        model.addAttribute("flats", flats);
        return "broadcastTechnics";
    }

    @PostMapping("/broadcast/{idNumber}")
    public String broadTechnics(Model model,
                                @PathVariable long idNumber,
                                @RequestParam int flatId,
                                @RequestParam Date date){
        Broadcast broadcast = new Broadcast();
        Flat flat = flatRepository.findById(flatId);
        Employee employee = employeeRepository.findBySubdivisionAndPosition(flat.getSubdivision(), Position.FINANCE);
        Technics technics = technicsRepository.findByIdNumber(idNumber);
        broadcast.setTechnics(technics);
        broadcast.setFromSubvision(technics.getFlat().getSubdivision());
        broadcast.setToEmployee(employee);
        broadcast.setDate(date);
        broadcastRepository.save(broadcast);
        technics.setFlat(flat);
        technics.setEmployee(employee);
        technicsRepository.save(technics);
        return "redirect:/tech";
    }

    @GetMapping("/broadcast")
    public String allBroadcast(Model model){
        Iterable<Broadcast> broadcasts = broadcastRepository.findAll();
        model.addAttribute("broadcasts", broadcasts);
        return "broadcast";
    }

}
