package com.highradius.salesManagement.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.highradius.salesManagement.dao.CustomerDao;
import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.Customer;
import com.highradius.salesManagement.util.HibernateUtil;

public class CustomerDaoImpl implements CustomerDao {

    @SuppressWarnings("unchecked")
	@Override
    public List<Customer> allCustomers() throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Customer");
            return query.list();
        } catch (Exception e) {
            throw new CustomException("Error while fetching all customers: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean exist(Customer customer) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(*) FROM Customer WHERE customerName = :customerName");
            query.setParameter("customerName", customer.getCustomerName());
            return (Long) query.uniqueResult() > 0;
        } catch (Exception e) {
            throw new CustomException("Error while checking if customer exists: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
	@Override
    public Customer customerById(int customerId) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Customer WHERE customerId = :customerId");
            query.setParameter("customerId", customerId);
            return (Customer) query.uniqueResult();
        } catch (Exception e) {
            throw new CustomException("Error while fetching customer by ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Customer> customersByCountry(List<String> countries) throws CustomException {
        Session session = null;
        List<Customer> customers = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Customer.class);
            criteria.add(Restrictions.in("country", countries));
            customers = criteria.list();
            return customers;
        } catch (Exception e) {
            throw new CustomException("Error while fetching customers by country: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public int deleteCustomersByCountry(String country) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Customer WHERE country = :country");
            query.setParameter("country", country);
            int deletedCount = query.executeUpdate();
            transaction.commit();
            return deletedCount;
        } catch (Exception e) {
            throw new CustomException("Error while deleting customers by country: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public int deleteCustomerById(int customerId) throws CustomException {
        Session session = null;
        int deletedCount = 0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            
            Query query = session.createQuery("DELETE FROM Customer WHERE customer_id = :customerId");
            query.setParameter("customerId", customerId);
            deletedCount = query.executeUpdate();
            transaction.commit();
            
        } catch (Exception e) {
        	e.printStackTrace();
            throw new CustomException("Error while deleting customer by ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return deletedCount;
    }

    @Override
    public int updateCustomer(Customer customer) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
            return customer.getCustomerId();
        } catch (Exception e) {
            throw new CustomException("Error while updating customer: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public int insertCustomer(Customer customer) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            int customerId = (int) session.save(customer);
            transaction.commit();
            return customerId;
        } catch (Exception e) {
            throw new CustomException("Error while adding customer: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public int insertMultipleCustomers(List<Customer> customers) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            for (Customer customer : customers) {
                session.save(customer);
            }
            transaction.commit();
            return customers.size();
        } catch (Exception e) {
            throw new CustomException("Error while inserting multiple customers: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}