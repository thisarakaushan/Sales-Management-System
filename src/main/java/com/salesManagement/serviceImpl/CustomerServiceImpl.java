package com.highradius.salesManagement.serviceImpl;

import java.util.List;

import com.highradius.salesManagement.dao.CustomerDao;
import com.highradius.salesManagement.daoImpl.CustomerDaoImpl;
import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.Customer;
import com.highradius.salesManagement.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	CustomerDao customerDao = new CustomerDaoImpl();

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<Customer> allCustomers() throws CustomException {
        try {
            return customerDao.allCustomers();
        } catch (Exception e) {
            throw new CustomException("Error while fetching all customers: " + e.getMessage());
        }
    }  

    @Override
    public Customer customerById(int customerId) throws CustomException {
        try {
            return customerDao.customerById(customerId);
        } catch (Exception e) {
            throw new CustomException("Error while fetching customer by ID: " + e.getMessage());
        }
    }

    @Override
    public List<Customer> customersByCountry(List<String> countries) throws CustomException {
        try {
            return customerDao.customersByCountry(countries);
        } catch (Exception e) {
            throw new CustomException("Error while fetching customers by country: " + e.getMessage());
        }
    }

    @Override
    public int insertCustomer(Customer customer) throws CustomException {
        try {
            if (customer == null) {
                throw new CustomException("Customer object is null. Please provide valid data.");
            }
            if (!customerDao.exist(customer)) {
                return customerDao.insertCustomer(customer);
            } else {
                throw new CustomException("Customer with the same name already exists.");
            }
        } catch (Exception e) {
            throw new CustomException("Error while inserting customer: " + e.getMessage());
        }
    }

    @Override
    public int updateCustomer(Customer customer) throws CustomException{
        try {
            if (customer == null) {
                throw new CustomException("Customer object is null. Customer does not exist for update.");
            }
            if (!customerDao.exist(customer)) {
                return customerDao.updateCustomer(customer);
            } else {
                throw new CustomException("Please provide valid data.");
            }
        } catch (Exception e) {
            throw new CustomException("Error while updating customer: " + e.getMessage());
        }
    }

    @Override
    public int insertMultipleCustomers(List<Customer> customers) throws CustomException {
        try {
            return customerDao.insertMultipleCustomers(customers);
        } catch (Exception e) {
            throw new CustomException("Error while inserting multiple customers: " + e.getMessage());
        }
    }

    @Override
    public int deleteCustomerById(int customerId) throws CustomException {
        try {
            return customerDao.deleteCustomerById(customerId);
        } catch (Exception e) {
            throw new CustomException("Error while deleting customer by ID: " + e.getMessage());
        }
    }

    @Override
    public int deleteCustomersByCountry(String country) throws CustomException {
        try {
            return customerDao.deleteCustomersByCountry(country);
        } catch (Exception e) {
            throw new CustomException("Error while deleting customers by country: " + e.getMessage());
        }
    }

}
