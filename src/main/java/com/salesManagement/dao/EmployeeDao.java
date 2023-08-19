package com.highradius.salesManagement.dao;

import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.Employee;

import java.util.List;

public interface EmployeeDao {
	
    List<Employee> allEmployees() throws CustomException;
    
    Employee employeeById(int employeeId) throws CustomException;
    
    int updateEmployee(Employee employee) throws CustomException;
    
    int deleteEmployeeById(int employeeId) throws CustomException;
    
    int insertEmployee(Employee employee) throws CustomException;
    
    int insertMultipleEmployees(List<Employee> employees) throws CustomException;

	//boolean exist(Employee employee) throws CustomException;
	
	int insertEmployeeWithPhoto(Employee employee, byte[] photo) throws CustomException;
	
	Employee employeeByIdWithPhoto(int employeeId) throws CustomException;


}
