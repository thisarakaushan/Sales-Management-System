package com.highradius.salesManagement.actions;

import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.Customer;
import com.highradius.salesManagement.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class CustomerActions {
	
	public Customer customer;
    public List<Customer> customers;
    public int customerId;
    public String country;
    public List<String> countries;  

	private CustomerService customerService;
    public ApplicationContext context;

    public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

    public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public CustomerActions() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        customerService = (CustomerService) context.getBean("customerServiceImpl");
    }
	
	/**
	 * Method for get all customers
	 * 
	 * @return
	 */
    public String allCustomers() {
        try {
            customers =  customerService.allCustomers();
            if (customers != null) {
                return "success";
            } else {
            	
            	throw new CustomException("No customer found.");
            }
            
        } catch (CustomException e) {
        	e.printStackTrace();
            throw new RuntimeException("Error fetching customers: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for get customer by Id
     * 
     * @return
     */
    public String customerById() {
        try {
        	customer = customerService.customerById(this.customerId);
        	if (customer != null) {
                return "success";
            } else {
            	
            	throw new CustomException("Customer not found for ID: " + this.customerId);
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error fetching customers: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for get customer by country
     * 
     * @return
     */
    public String customersByCountry() {
        try {
            customers = customerService.customersByCountry(countries);
            if(customers != null) {
            	return "success";
            } else {
            	
            	throw new CustomException("Customer not found for Country: " + this.country);
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error fetching customers: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for insert customer
     * 
     * @return
     */
    public String insertCustomer() {
        try {
            int result = customerService.insertCustomer(customer);
            return (result > 0) ? "added" : "error";
        } catch (CustomException e) {
            throw new RuntimeException("Error inserting customer: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for update customer
     * 
     * @return
     */
    public String updateCustomer() {
        try {
            int result = customerService.updateCustomer(customer);
            return (result > 0) ? "updated" : "error";
        } catch (CustomException e) {
            throw new RuntimeException("Error updating customers: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for delete customer by id
     * 
     * @return
     */
    public String deleteCustomerById() {
        try {
            int result = customerService.deleteCustomerById(customerId);
            return (result > 0) ? "deleted" : "error";
        } catch (CustomException e) {
            throw new RuntimeException("Error deleting customer: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for delete customer by country
     * 
     * @return
     */
    public String deleteCustomersByCountry() {
    	System.out.println(customerId);
        try {
            int result = customerService.deleteCustomersByCountry(country);
            return (result > 0) ? "deleted" : "error";
        } catch (CustomException e) {
            throw new RuntimeException("Error deleting customer: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for insert multiple customers
     * 
     * @return
     * @throws CustomException
     */
    public String insertMultipleCustomers() throws CustomException {
        try {
            int result = customerService.insertMultipleCustomers(customers);
            return (result > 0) ? "added" : "error";
        } catch (CustomException e) {
            throw new RuntimeException("Error inserting customers: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }
}

