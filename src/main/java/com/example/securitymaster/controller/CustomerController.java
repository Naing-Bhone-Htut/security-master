package com.example.securitymaster.controller;

import com.example.securitymaster.dao.CustomerDao;
import com.example.securitymaster.ds.Customer;
import com.example.securitymaster.security.annotation.customers.CustomersCreate;
import com.example.securitymaster.security.annotation.customers.CustomersDelete;
import com.example.securitymaster.security.annotation.customers.CustomersPageView;
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

import javax.naming.Binding;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @CustomersPageView
    @GetMapping("/customers")
    public ModelAndView findAllCustomers(){
        return new ModelAndView("customers","customers",customerDao.findAll());
    }

    @CustomersCreate
    @GetMapping("/customer-form")
    public String customerFrom(Model model){
        model.addAttribute("customer",new Customer());
        return "customer-form";
    }

    @CustomersCreate
    @PostMapping("/customer-form")
    public String saveCustomer(@Valid Customer customer, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "customer-form";
        }
        customerDao.save(customer);
        return "redirect:/customers/customers";
    }

    @CustomersDelete
    @GetMapping("/customers/delete")
    public String deleteCustomer(@RequestParam("id")int id){
        if(customerDao.existsById(id)){
            customerDao.deleteById(id);
            return "redirect:/customers/customers";
        }else {
            throw new EntityNotFoundException(id + "Not Found!");
        }
    }

}
