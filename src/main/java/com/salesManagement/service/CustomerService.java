package com.highradius.salesManagement.service;

import java.util.List;

import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.Customer;

public interface CustomerService {
 
	List<Customer> allCustomers() throws CustomException;
	
	Customer customerById(int customerId) throws CustomException;
	
	List<Customer> customersByCountry(List<String> countries) throws CustomException;
	
	int updateCustomer(Customer customer) throws CustomException;
	
	int deleteCustomerById(int customerId) throws CustomException;
	
	int deleteCustomersByCountry(String country) throws CustomException;
	
	int insertCustomer(Customer customer) throws CustomException;
	
	int insertMultipleCustomers(List<Customer> customers) throws CustomException;
	
}
