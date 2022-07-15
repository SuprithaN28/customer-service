package com.Assignment.CustomerService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Assignment.CustomerService.entity.Customer;
import com.Assignment.CustomerService.repository.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/addcustomer")
	public String addCustomer(@RequestBody Customer customer) {
		logger.info("Registering customer with customet id " + customer.getCusId() + " to HashKart.");
		String pwd = customer.getPassword();
		String encryptedpwd = passwordEncoder.encode(pwd);
		customer.setPassword(encryptedpwd);
		customerRepository.save(customer);
		return "You have successfully registered in HashKart";

	}

	@GetMapping("/findbyemail/{email}")
	public Customer findByEmail(@PathVariable String email) {
		logger.info("Fetching customer details with email "+email);
		return customerRepository.findByEmail(email);
	}
}
