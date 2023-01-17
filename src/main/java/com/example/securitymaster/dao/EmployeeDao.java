package com.example.securitymaster.dao;

import com.example.securitymaster.ds.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDao extends CrudRepository<Employee,Integer> {
}
