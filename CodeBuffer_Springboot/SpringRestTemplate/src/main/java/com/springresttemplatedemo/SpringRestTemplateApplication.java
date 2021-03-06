package com.springresttemplatedemo;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import com.springresttemplatedemo.entity.CustomerDTO;

@CrossOrigin
@SpringBootApplication
public class SpringRestTemplateApplication implements CommandLineRunner {

	private static final Log LOGGER = LogFactory.getLog(SpringRestTemplateApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRestTemplateApplication.class, args);
	}

	@Override
	public void run(String...args)throws Exception {
		//getCustomerDetails(2);
		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerId(7);
		customer.setName("Nitu Chouhan");
		customer.setEmailId("nitusandeep@gmail.com");
		customer.setDateOfBirth(LocalDate.parse("1992-11-24"));
		//addCustomerDetails(customer);
		//updateCustomerdetails(customer);
		deleteCustomer(7);
	}
	
	private void addCustomerDetails(CustomerDTO customer) {
		String url="http://localhost:8081/infybank/customers";
		RestTemplate restTemplate = new RestTemplate();
		String responseMessage = restTemplate.postForObject(url, customer, String.class);
		LOGGER.info(responseMessage);
	}

	public void getCustomerDetails(Integer customerId) {
		String url="http://localhost:8081/infybank/customers/2";
		RestTemplate restTemplate = new RestTemplate();
		CustomerDTO customer = restTemplate.getForObject(url, CustomerDTO.class,customerId);
		LOGGER.info(customer);
	}
	
	public void updateCustomerdetails(CustomerDTO customer) {
		String url="http://localhost:8081/infybank/customers/7";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(url, customer, customer.getCustomerId());
		LOGGER.info("Customer Updated Successfully");
	}
	
	public void deleteCustomer(Integer customerId) {
		String url="http://localhost:8081/infybank/customers/7";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(url, customerId);
		LOGGER.info("Customer Information Deleted Successfully");
	}
}
