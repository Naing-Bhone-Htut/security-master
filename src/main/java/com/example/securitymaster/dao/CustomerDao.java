package com.example.securitymaster.dao;

import com.example.securitymaster.ds.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<Customer,Integer> {

}
