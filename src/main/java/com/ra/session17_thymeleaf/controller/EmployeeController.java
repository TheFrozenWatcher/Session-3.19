package com.ra.session17_thymeleaf.controller;

import com.ra.session17_thymeleaf.entity.Employee;
import com.ra.session17_thymeleaf.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeController
{
    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService)
    {
        this.employeeService = employeeService;
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder)
//    {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
//    }

    @RequestMapping({"/", "/list"})
    public String list(Model model)
    {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("list", employees);
        return "listEmployee";
    }

    @GetMapping("/initAdd")
    public String viewAdd(Model model)
    {
        Employee employee = new Employee();
        model.addAttribute("em", employee);
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@Validated @ModelAttribute("em") Employee employee, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("em", employee);
            return "addEmployee";
        } else
        {
            employeeService.save(employee);
            return "redirect:/list";
        }
    }

    @GetMapping("/initEdit/{id}")
    public String viewEdit(@PathVariable("id") Integer id, Model model)
    {
        model.addAttribute("em", employeeService.findById(id));
        return "editEmployee";
    }

    @PostMapping("/editEmployee")
    public String editEmployee(@Valid @ModelAttribute("em") Employee employee, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("em", employee);
            return "editEmployee";
        }
        employeeService.save(employee);
        return "redirect:/list";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id)
    {
        employeeService.delete(id);
        return "redirect:/list";
    }
}
