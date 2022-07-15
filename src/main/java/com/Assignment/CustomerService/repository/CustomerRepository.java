package com.Assignment.CustomerService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Assignment.CustomerService.entity.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
//	@Query(value= "select c.email from HashKartApp c where c.email=:email")
	Customer findByEmail(String email);
	

}