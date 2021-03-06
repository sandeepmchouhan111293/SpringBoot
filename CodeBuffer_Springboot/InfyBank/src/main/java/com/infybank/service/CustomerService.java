package com.infybank.service;

import java.util.List;

import com.infybank.dto.CustomerDTO;
import com.infybank.entity.Customer;
import com.infybank.exception.InfyBankException;
import com.infybank.utility.InfyBankExceptionHandler;

public interface CustomerService {

	public List<CustomerDTO> getAllCustomerDetails() throws InfyBankException;

	public CustomerDTO getCustomerDetails(Integer customerId) throws InfyBankException;

	public Integer addCustomer(CustomerDTO customer);

	public void deleteCustomer(Integer customerId);

	public void updateCustomer(Integer customerId,Customer cust) throws InfyBankException;

}
