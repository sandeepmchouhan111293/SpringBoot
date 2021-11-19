package com.infybank.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infybank.dto.CustomerDTO;
import com.infybank.entity.Customer;
import com.infybank.exception.InfyBankException;
import com.infybank.service.CustomerService;
import com.infybank.utility.InfyBankExceptionHandler;

@RestController
@RequestMapping(value="/infybank")
@Validated
public class CustomerAPIController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private Environment environment;
	
	@RequestMapping(value="/customers",method=RequestMethod.GET)
	public ResponseEntity<List<CustomerDTO>> getAllCustomerDetails() throws InfyBankException{
		return new ResponseEntity<>(customerService.getAllCustomerDetails(),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/customers/{customerId}",method=RequestMethod.GET)
	public  ResponseEntity<CustomerDTO> getCustomerDetails
	(@PathVariable("customerId") @Min(value = 1, message = "Customer id should be between 1 and 100") 
	@Max(value = 100, message = "Customer id should be between 1 and 100") Integer customerId) throws InfyBankException{
		CustomerDTO customer = customerService.getCustomerDetails(customerId);
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	@RequestMapping(value="/customers",method=RequestMethod.POST)
	public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerDTO customer){
		Integer id = customerService.addCustomer(customer);
		String message = environment.getProperty("API.INSERT_SUCCESS")+id;
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/customers/{customerId}",method=RequestMethod.PUT)
	public ResponseEntity<String> updateCustomer(@PathVariable("customerId") Integer customerId,@RequestBody Customer customer) throws InfyBankException{
		customerService.updateCustomer(customerId,customer);
		String message = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
		
	@RequestMapping(value="/customers/{customerId}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Integer customerId){
		customerService.deleteCustomer(customerId);
		String successMessage = environment.getProperty("API.DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);	
	}
}
