package corseproject.controller;

import corseproject.domain.Employee;
import corseproject.domain.Subdivision;
import corseproject.domain.Technics;
import corseproject.repos.EmployeeRepository;
import corseproject.repos.SubdivisionRepository;
import corseproject.repos.TechnicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class RedactEmployeeController {
    @Autowired
    private SubdivisionRepository subdivisionRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TechnicsRepository technicsRepository;


    @GetMapping()
    public String getEmployee(Model model){
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employees";
    }
    @GetMapping("/redact/{employeeId}")
    public  String redactEmployeePage(Model model,
                               @PathVariable long employeeId)  {
        Employee employee = employeeRepository.findById(employeeId);
        model.addAttribute("employee", employee);
        return "redactEmployee";

    }

    @PostMapping("/redact/{employeeId}")
    public  String redactEmployee(Model model,
                                  @PathVariable long employeeId,
                                  @RequestParam String name)  {
        Employee employee = employeeRepository.findById(employeeId);
        employee.setName(name);
        employeeRepository.save(employee);
        return "redirect:/employee/redact/" + employeeId;

    }

    @PostMapping("/delete/{employeeId}")
    public  String deleteEmployee(Model model,
                                  @PathVariable long employeeId)  {
        Employee employee = employeeRepository.findById(employeeId);
        List<Technics> technics = technicsRepository.findByEmployee(employee);
        technicsRepository.deleteAll(technics);
        employeeRepository.delete(employee);
        return "redirect:/employee";

    }
}
