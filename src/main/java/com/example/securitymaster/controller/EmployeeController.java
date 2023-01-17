package com.example.securitymaster.controller;

import com.example.securitymaster.dao.EmployeeDao;
import com.example.securitymaster.ds.Employee;
import com.example.securitymaster.security.annotation.employees.EmployeesCreate;
import com.example.securitymaster.security.annotation.employees.EmployeesDelete;
import com.example.securitymaster.security.annotation.employees.EmployeesPageView;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;

    @EmployeesPageView
    @GetMapping("/employees")
    public ModelAndView findAllEmployee(){
        return new ModelAndView("employees","employees",employeeDao.findAll());
    }

    @EmployeesCreate
    @GetMapping("/employee-form")
    public String employeeForm(Model model){
        model.addAttribute("employee",new Employee());
        return "employee-form";
    }

    @EmployeesCreate
    @PostMapping("/employee-form")
    public String saveEmployee(@Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
            return "/employee-form";
        }else {
            employeeDao.save(employee);
            return "redirect:/employees/employees";
        }
    }

    @EmployeesDelete
    @GetMapping("/employees/delete")
    public String deleteEmployee(@RequestParam("id")int id){
        if(employeeDao.existsById(id)){
            employeeDao.deleteById(id);
            return "redirect:/employees/employees";
        }else {
            throw new EntityNotFoundException(id + "cannot found! Naruto Kun");
        }
    }

}
