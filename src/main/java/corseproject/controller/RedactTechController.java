package corseproject.controller;

import corseproject.domain.*;
import corseproject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
@RequestMapping("/tech")
public class RedactTechController {
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


    @GetMapping()
    public String getTechnics(Model model){
        Iterable<Technics> technics = technicsRepository.findAll();
        model.addAttribute("technicses", technics);
        return "alltechnics";
    }

    @GetMapping("/add")
    public String addTechnics(Model model){
        Iterable<Flat> flats = flatRepository.findAll();
        model.addAttribute("flats", flats);
        return "addTechnics";
    }

    @PostMapping("/add")
    public String createTechnics(@RequestParam long idNumber,
                                 @RequestParam String title,
                                 @RequestParam String model,
                                 @RequestParam Date date,
                                 @RequestParam int cash,
                                 @RequestParam long flatId){
        Flat flat = flatRepository.findById(flatId);
        Employee employee = employeeRepository.findBySubdivisionAndPosition(flat.getSubdivision(), Position.FINANCE);
        Technics technics = new Technics();
        technics.setTitle(title);
        technics.setIdNumber(idNumber);
        technics.setCash(cash);
        technics.setModel(model);
        technics.setDatePurchase(date);
        technics.setEmployee(employee);
        technics.setFlat(flat);
        technicsRepository.save(technics);

        return "redirect:/tech";
    }

    @GetMapping("/delete/{idNumber}")
    public String deleteTechnics(@PathVariable long idNumber){
        Technics technics = technicsRepository.findByIdNumber(idNumber);
        technicsRepository.delete(technics);
        return "redirect:/tech";
    }

    @GetMapping("/redact/{idNumber}")
    public String redactTechnics(Model model,
                                 @PathVariable long idNumber){
        Technics technics = technicsRepository.findByIdNumber(idNumber);
        Iterable<Flat> flats = flatRepository.findAll();
        model.addAttribute("technics", technics);
        model.addAttribute("flats", flats);
        return "redactTechnics";
    }

    @PostMapping("/redact/{idNumber}")
    public String updateTechnics(@PathVariable long idNumber,
                                 @RequestParam String title,
                                 @RequestParam String model,
                                 @RequestParam Date date,
                                 @RequestParam int cash,
                                 @RequestParam long flatId){
        Flat flat = flatRepository.findById(flatId);
        Employee employee = employeeRepository.findBySubdivisionAndPosition(flat.getSubdivision(), Position.FINANCE);
        Technics technics = technicsRepository.findByIdNumber(idNumber);
        technics.setTitle(title);
        technics.setIdNumber(idNumber);
        technics.setCash(cash);
        technics.setModel(model);
        technics.setDatePurchase(date);
        technics.setEmployee(employee);
        technics.setFlat(flat);
        technicsRepository.save(technics);

        return "redirect:/tech";
    }
}
