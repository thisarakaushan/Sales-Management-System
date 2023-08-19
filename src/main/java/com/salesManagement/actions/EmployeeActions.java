package com.highradius.salesManagement.actions;

import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.Employee;
import com.highradius.salesManagement.service.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.Model;

import java.util.Base64;
import java.util.List;

public class EmployeeActions {
    
    Employee employee;
    List<Employee> employees;
    int employeeId;

    public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	private EmployeeService employeeService;
    private ApplicationContext context;

    public EmployeeActions() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        employeeService = (EmployeeService) context.getBean("employeeServiceImpl");
    }

    /**
     * method for Get all employees
     * 
     * @return
     */
    public String allEmployees() {
        try {
            employees = employeeService.allEmployees();
            if (employees != null) {
                return "success";
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error fetching employees: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
        return "error";
    }

    /**
     * method for Get employee by employee_id
     * 
     * @return
     */
    public String employeeById() {
        try {
            employee = employeeService.employeeById(employeeId);
            if (employee != null) {
                return "success";
            } else {
            	
            	throw new CustomException("Employee not found for Id: " + this.employeeId);
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error fetching employee: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * method for Update employee by employee_id
     * 
     * @return
     */
    public String updateEmployee() {
        try {
            //employee.setEmployeeId(employeeId);
            int result = employeeService.updateEmployee(employee);
            return (result > 0) ? "updated" : "error";
        } catch (CustomException e) {
            throw new RuntimeException("Error updating employee: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * method for Delete employee by employee_id
     * 
     * @return
     */
    public String deleteEmployeeById() {
        try {
            int result = employeeService.deleteEmployeeById(employeeId);
            return (result > 0) ? "deleted" : "error";
        } catch (CustomException e) {
            throw new RuntimeException("Error deleting customer: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * method for Insert single employee
     * 
     * @return
     */
    public String insertEmployee() {
        try {
            int result = employeeService.insertEmployee(employee);
            return (result > 0) ? "added" : "error";
        } catch (CustomException e) {
            throw new RuntimeException("Error inserting employee: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * method for Insert multiple employees
     * 
     * @return
     */
    public String insertMultipleEmployees() {
        try {
            int result = employeeService.insertMultipleEmployees(employees);
            return (result > 0) ? "added" : "error";
        } catch (CustomException e) {
            throw new RuntimeException("Error inserting employees: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }
    
    /**
     * method for Add employee with photo
     * 
     * @param employee
     * @param photoBytes
     * @return
     */
    public String addEmployeeWithPhoto(Employee employee, byte[] photoBytes) {
        try {
            employee.setPhoto(photoBytes);

            int result = employeeService.insertEmployee(employee);
            return (result > 0) ? "added" : "error"; 
        } catch (CustomException e) {
            throw new RuntimeException("Error inserting employee: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }
    
    /**
     * method for  Get employee by employee_id with photo
     * 
     * @param employeeId
     * @param model
     * @return
     */
    public String employeeByIdWithPhoto(int employeeId, Model model) {
        try {
            Employee employee = employeeService.employeeById(employeeId);
            if (employee != null) {
                byte[] photoBytes = employee.getPhoto();
                if (photoBytes != null) {
                	// an image or any binary data into a Base64-encoded string
                    String base64Photo = Base64.getEncoder().encodeToString(photoBytes);
                    model.addAttribute("employeePhoto", base64Photo);
                }
                model.addAttribute("employee", employee);
                return "employeeProfile"; // Assuming this is your view name
            } else {
                return "error";
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error fetching employee: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }
}
