package net.springboot.examples;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/employees")
    public ModelAndView showCities() {
        List<Employee> employees = employeeRepository.findAll();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("employees", employees);

        return new ModelAndView("employees", params);
    }
}
