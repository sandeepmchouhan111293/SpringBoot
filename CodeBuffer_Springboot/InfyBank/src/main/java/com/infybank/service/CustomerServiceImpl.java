package com.infybank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infybank.dto.CustomerDTO;
import com.infybank.entity.Customer;
import com.infybank.exception.InfyBankException;
import com.infybank.repository.CustomerRepository;
import com.infybank.utility.InfyBankExceptionHandler;

@Service(value="customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService{

	private static Logger log = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	
	@Autowired
	private CustomerRepository customerRepository;
		
	@Override
	public List<CustomerDTO> getAllCustomerDetails() throws InfyBankException {
		Iterable<Customer> customers = customerRepository.findAll();
		List<CustomerDTO> customerList = new ArrayList<>();
		customers.forEach(customer ->{
			CustomerDTO cust = new CustomerDTO();
			cust.setCustomerId(customer.getCustomerId());
			cust.setName(customer.getName());
			cust.setEmailId(customer.getEmailId());
			cust.setDateOfBirth(customer.getDateOfBirth());
			customerList.add(cust);
		});
		if(customerList.isEmpty()) {
			
			throw new InfyBankException("Service.CUSTOMERS_NOT_FOUND");
		}
			
		return customerList;
	}

	@Override
	public CustomerDTO getCustomerDetails(Integer customerId) throws InfyBankException {
		Optional<Customer> tempCustomer = customerRepository.findById(customerId);
		if(!tempCustomer.isPresent()) {
			log.warn(new InfyBankException("Service.CUSTOMERS_NOT_FOUND"));
			throw new InfyBankException("Service.CUSTOMER_NOT_FOUND");
		}else {
		Customer customer = tempCustomer.get();
		CustomerDTO dtoObject = new CustomerDTO();
		if(customer.getCustomerId() != null)	dtoObject.setCustomerId(customer.getCustomerId());
		if(!customer.getName().isEmpty() && customer.getName() != "")	dtoObject.setName(customer.getName());
		if(customer.getDateOfBirth() != null )	dtoObject.setDateOfBirth(customer.getDateOfBirth());
		if(customer.getEmailId() != null)	dtoObject.setEmailId(customer.getEmailId());
		return dtoObject;
		}
	}

	@Override
	public Integer addCustomer(CustomerDTO customer) {	
		Customer customer1 = new Customer();
		customer1.setCustomerId(customer.getCustomerId());
		customer1.setEmailId(customer.getEmailId());
		customer1.setName(customer.getName());
		customer1.setDateOfBirth(customer.getDateOfBirth());
		
		Customer cust =customerRepository.save(customer1);
		return cust.getCustomerId();
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		customerRepository.deleteById(customerId);
	}

	@Override
	public void updateCustomer(Integer customerId, Customer cust) throws InfyBankException {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if(customer.isPresent()) {
			Customer tempCustomer =customer.get();
			tempCustomer.setName(cust.getName());
			tempCustomer.setEmailId(cust.getEmailId());
			tempCustomer.setDateOfBirth(cust.getDateOfBirth());
			customerRepository.save(tempCustomer);
		}	
	}

}
