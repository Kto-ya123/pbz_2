package corseproject.controller;

import corseproject.domain.*;
import corseproject.repos.EmployeeRepository;
import corseproject.repos.FlatRepository;
import corseproject.repos.SubdivisionRepository;
import corseproject.repos.TechnicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subdivision")
public class SubdivisionController {
    @Autowired
    private SubdivisionRepository subdivisionRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private FlatRepository flatRepository;
    @Autowired
    private TechnicsRepository technicsRepository;


    @GetMapping()
    public  String subdivision(Model model)  {
        Iterable<Subdivision> subdivisions = subdivisionRepository.findAll();
        model.addAttribute("subdivisions", subdivisions);
        return "allsubdivision";

    }

    @GetMapping("/add")
    public  String createSubdivision()  {
        return "addSubdivision";
    }

    @PostMapping("/add")
    public String addSubdivision(@RequestParam String name,
                                 @RequestParam String fullName,
                                 @RequestParam long number){
        Subdivision subdivision = new Subdivision();
        subdivision.setName(name);
        subdivision.setFullName(fullName);
        subdivision.setId(number);
        subdivisionRepository.save(subdivision);

        return "redirect:/subdivision";
    }

    @GetMapping("/delete/{subdivisionId}")
    public  String deleteSubdivision(@PathVariable long subdivisionId){
        Subdivision subdivision = subdivisionRepository.findById(subdivisionId);
        employeeRepository.deleteAll(employeeRepository.findBySubdivision(subdivision));
        flatRepository.deleteAll(flatRepository.findBySubdivision(subdivision));
        subdivisionRepository.delete(subdivision);
        return "redirect:/subdivision";
    }

    @PostMapping("/addemployee/{subdivisionId}")
    public String addEmployee(@PathVariable long subdivisionId,
                              @RequestParam String name,
                              @RequestParam String position){
        Subdivision subdivision = subdivisionRepository.findById(subdivisionId);
        Employee employee = new Employee();
        employee.setName(name);
        employee.setPosition(Position.getPosFromString(position));
        employee.setSubdivision(subdivision);
        employeeRepository.save(employee);
        return "redirect:/subdivision/" + subdivisionId;
    }

    @PostMapping("/addflat/{subdivisionId}")
    public String addFlat(@PathVariable long subdivisionId,
                              @RequestParam long number,
                              @RequestParam int square){
        Subdivision subdivision = subdivisionRepository.findById(subdivisionId);
        Flat flat = new Flat();
        flat.setId(number);
        flat.setSquare(square);
        flat.setSubdivision(subdivision);
        flatRepository.save(flat);
        return "redirect:/subdivision/" + subdivisionId;
    }

    @GetMapping("/{subdivisionId}")
    public  String subdivision(Model model, @PathVariable long subdivisionId)  {
        Subdivision subdivision = subdivisionRepository.findById(subdivisionId);
        Iterable<Employee> employees = employeeRepository.findBySubdivision(subdivision);
        Iterable<Flat> flats = flatRepository.findBySubdivision(subdivision);
        Employee employee = employeeRepository.findBySubdivisionAndPosition(subdivision, Position.FINANCE);
        if(employee != null) {
            Iterable<Technics> technics = technicsRepository.findByEmployee(employee);
            model.addAttribute("technicses", technics);
        }

        model.addAttribute("subdivision", subdivision);
        model.addAttribute("employees", employees);
        model.addAttribute("flats", flats);

        return "subdivisionPage";

    }


}
