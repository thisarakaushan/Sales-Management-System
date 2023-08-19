package com.highradius.salesManagement.daoImpl;

import com.highradius.salesManagement.dao.EmployeeDao;
import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.Employee;
import com.highradius.salesManagement.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> allEmployees() throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Employee");
            return query.list();
        } catch (Exception e) {
            throw new CustomException("Error while fetching all employees: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Employee employeeById(int employeeId) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return (Employee) session.get(Employee.class, employeeId);
        } catch (Exception e) {
            throw new CustomException("Error while fetching employee by ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
//    @Override
//    public boolean exist(Employee employee) throws CustomException {
//        Session session = null;
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            Query query = session.createQuery(
//                "SELECT COUNT(*) FROM Employee WHERE employeeId != :id AND " +
//                "employeeFirstName = :firstName AND employeeLastName = :lastName"
//            );
//            query.setParameter("id", employee.getEmployeeId());
//            query.setParameter("firstName", employee.getFirstName());
//            query.setParameter("lastName", employee.getLastName());
//            return (Long) query.uniqueResult() > 0;
//        } catch (Exception e) {
//            throw new CustomException("Error while checking if employee exists: " + e.getMessage());
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//    }


    @Override
    public int updateEmployee(Employee employee) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            return employee.getEmployeeId();
        } catch (Exception e) {
            throw new CustomException("Error while updating employee: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public int deleteEmployeeById(int employeeId) throws CustomException {
        Session session = null;
        int deletedCount = 0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            
            Query query = session.createQuery("DELETE FROM Employee WHERE employee_id = :employeeId");
            query.setParameter("employeeId", employeeId);
            deletedCount = query.executeUpdate();
            transaction.commit();
            
        } catch (Exception e) {
            throw new CustomException("Error while deleting employee by ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return deletedCount;
    }

    @Override
    public int insertEmployee(Employee employee) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            int employeeId = (int) session.save(employee);
            transaction.commit();
            return employeeId;
        } catch (Exception e) {
            throw new CustomException("Error while adding employee: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public int insertMultipleEmployees(List<Employee> employees) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            for (Employee employee : employees) {
                session.save(employee);
            }
            transaction.commit();
            return employees.size();
        } catch (Exception e) {
            throw new CustomException("Error while inserting multiple employees: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    // photo Logic implementation
    
    /**
     * method for insert photo
     * 
     */
    
    @Override
    public int insertEmployeeWithPhoto(Employee employee, byte[] photo) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            employee.setPhoto(photo);
            int employeeId = (int) session.save(employee);
            transaction.commit();
            return employeeId;
        } catch (Exception e) {
            throw new CustomException("Error while adding employee with photo: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public Employee employeeByIdWithPhoto(int employeeId) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Employee employee = (Employee) session.get(Employee.class, employeeId);
            return employee;
        } catch (Exception e) {
            throw new CustomException("Error while fetching employee by ID with photo: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
