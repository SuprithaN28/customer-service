package com.Assignment.CustomerService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


import com.Assignment.CustomerService.controller.CustomerController;
import com.Assignment.CustomerService.entity.Customer;
import com.Assignment.CustomerService.repository.CustomerRepository;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CustomerControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	CustomerRepository customerRepository;
	
	@MockBean
	BCryptPasswordEncoder passwordEncoder;

	@Test
	void addCustomer() throws Exception {
		
		
	     Customer customer = new Customer(1, "suprin","suprin@gmail", "password", "test", 677676);
	              

	        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
	        
	        when(passwordEncoder.encode(any())).thenReturn("abc");

		mockMvc.perform(MockMvcRequestBuilders.post("/customer/addcustomer").with(csrf())
				.contentType(MediaType.APPLICATION_JSON).content(
						"{\"cusId\": \"1\",\"cusName\": \"pavan\",\"password\": \"admin\", \"email\": \"suprin@gmail\", \"address\": \"mysore\", \"phoneNo\": \"687687\"}"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	void findByEmail() throws Exception {
		
		   Customer customer = new Customer(1, "suprin","suprin@gmail", "password", "test", 677676);
			 when(customerRepository.findByEmail(anyString())).thenReturn(customer);

		mockMvc.perform(MockMvcRequestBuilders.get("/customer/findbyemail/suprin@gmail").with(csrf()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
