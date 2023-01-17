package com.example.securitymaster.controller;

import com.example.securitymaster.dao.CustomerDao;
import com.example.securitymaster.dao.DepartmentDao;
import com.example.securitymaster.ds.Customer;
import com.example.securitymaster.ds.Department;
import com.example.securitymaster.security.annotation.customers.CustomersCreate;
import com.example.securitymaster.security.annotation.customers.CustomersDelete;
import com.example.securitymaster.security.annotation.customers.CustomersPageView;
import com.example.securitymaster.security.annotation.departments.DepartmentsCreate;
import com.example.securitymaster.security.annotation.departments.DepartmentsDelete;
import com.example.securitymaster.security.annotation.departments.DepartmentsPageView;
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
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentDao departmentDao;

    @DepartmentsPageView
    @GetMapping("/departments")
    public ModelAndView findAllDepartment(){
        return new ModelAndView("departments","departments",departmentDao.findAll());
    }

    @DepartmentsCreate
    @GetMapping("/department-form")
    public String departmentForm(Model model){
        model.addAttribute("department",new Department());
        return "department-form";
    }

    @DepartmentsCreate
    @PostMapping("/department-form")
    public String saveDepartment(@Valid Department department, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "department-form";
        }
        departmentDao.save(department);
        return "redirect:/departments/departments";
    }

    @DepartmentsDelete
    @GetMapping("/departments/delete")
    public String deleteDepartment(@RequestParam("id")int id){
        if(departmentDao.existsById(id)){
            departmentDao.deleteById(id);
            return "redirect:/departments/departments";
        }else {
            throw new EntityNotFoundException(id + "Not Found!");
        }
    }

}
