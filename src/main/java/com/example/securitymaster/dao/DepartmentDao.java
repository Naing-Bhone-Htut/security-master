package com.example.securitymaster.dao;

import com.example.securitymaster.ds.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentDao extends CrudRepository<Department,Integer> {
}
