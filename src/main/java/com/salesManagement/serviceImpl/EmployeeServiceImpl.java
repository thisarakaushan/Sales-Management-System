package com.highradius.salesManagement.serviceImpl;

import com.highradius.salesManagement.dao.EmployeeDao;
import com.highradius.salesManagement.daoImpl.EmployeeDaoImpl;
import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.Employee;
import com.highradius.salesManagement.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDao employeeDao = new EmployeeDaoImpl();

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    } 	

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> allEmployees() throws CustomException {
        try {
            return employeeDao.allEmployees();
        } catch (Exception e) {
            throw new CustomException("Error while fetching all employees: " + e.getMessage());
        }
    }

    @Override
    public Employee employeeById(int employeeId) throws CustomException {
        try {
            return employeeDao.employeeById(employeeId);
        } catch (Exception e) {
            throw new CustomException("Error while fetching employee by ID: " + e.getMessage());
        }
    }

    @Override
    public int updateEmployee(Employee employee) throws CustomException {
        try {
//            if (employee == null) {
//                throw new CustomException("Employee object is null. Employee does not exist for update.");
//            }
//            if (!employeeDao.exist(employee)) {
//                return employeeDao.updateEmployee(employee);
//            } else {
//                throw new CustomException("Please provide valid data.");
//            }
            return employeeDao.updateEmployee(employee);
        } catch (Exception e) {
            throw new CustomException("Error while updating employee: " + e.getMessage());
        }
    }

    @Override
    public int deleteEmployeeById(int employeeId) throws CustomException {
        try {
            return employeeDao.deleteEmployeeById(employeeId);
        } catch (Exception e) {
            throw new CustomException("Error while deleting employee by ID: " + e.getMessage());
        }
    }
    
//    @Override
//    public boolean exist(Employee employee) throws CustomException {
//        try {
//            return employeeDao.exist(employee);
//        } catch (Exception e) {
//            throw new CustomException("Error while checking if employee exists: " + e.getMessage());
//        }
//    }


    @Override
    public int insertEmployee(Employee employee) throws CustomException {
        try {
            if (employee == null) {
                throw new CustomException("Employee object is null. Please provide valid data.");
            }
//            if (!employeeDao.exist(employee)) {
//                return employeeDao.insertEmployee(employee);
//            } else {
//                throw new CustomException("Employee with the same ID already exists.");
//            }
            return employeeDao.insertEmployee(employee);
        } catch (Exception e) {
            throw new CustomException("Error while inserting employee: " + e.getMessage());
        }
    }

    @Override
    public int insertMultipleEmployees(List<Employee> employees) throws CustomException {
        try {
            return employeeDao.insertMultipleEmployees(employees);
        } catch (Exception e) {
            throw new CustomException("Error while inserting multiple employees: " + e.getMessage());
        }
    }
    
    // Photo Logic implementation
    
    @Override
    public int insertEmployeeWithPhoto(Employee employee, byte[] photo) throws CustomException {
        try {
            if (employee == null || photo == null) {
                throw new CustomException("Employee object or photo is null. Please provide valid data.");
            }
//            if (!employeeDao.exist(employee)) {
//                return employeeDao.insertEmployeeWithPhoto(employee, photo);
//            } else {
//                throw new CustomException("Employee with the same ID already exists.");
//            }
            return employeeDao.insertEmployeeWithPhoto(employee, photo);
        } catch (Exception e) {
            throw new CustomException("Error while inserting employee with photo: " + e.getMessage());
        }
    }
    
    @Override
    public Employee employeeByIdWithPhoto(int employeeId) throws CustomException {
        try {
            return employeeDao.employeeByIdWithPhoto(employeeId);
        } catch (Exception e) {
            throw new CustomException("Error while fetching employee by ID with photo: " + e.getMessage());
        }
    }


}
