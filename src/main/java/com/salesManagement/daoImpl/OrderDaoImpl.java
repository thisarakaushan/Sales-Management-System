package com.highradius.salesManagement.daoImpl;

import com.highradius.salesManagement.dao.OrderDao;
import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.Order;
import com.highradius.salesManagement.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> allOrders() throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Order");
            return query.list();
        } catch (Exception e) {
            throw new CustomException("Error while fetching all orders: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

	@Override
    public Order orderById(int orderId) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Order WHERE orderId = :orderId");
            query.setParameter("orderId", orderId);
            return (Order) query.uniqueResult();
        } catch (Exception e) {
            throw new CustomException("Error while fetching order by ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

	@SuppressWarnings("unchecked")
	@Override
    public List<Order> ordersByOrderDate(Date orderDate) throws CustomException {
        Session session = null;
        List<Order> orders = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Order.class);
            criteria.add(Restrictions.eq("orderDate", orderDate));
            orders = criteria.list();
            return orders;
        } catch (Exception e) {
            throw new CustomException("Error while fetching orders by order date: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
	
	@Override
    public Order ordersByCustomerId(int customerId) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Order.class);
            criteria.add(Restrictions.eq("customer.customerId", customerId));
            Order order = (Order) criteria.uniqueResult();
            return order;
            
        } catch (Exception e) {
            throw new CustomException("Error while fetching orders by customer ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

	@Override
    public Order ordersByEmployeeId(int employeeId) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Order.class);
            criteria.add(Restrictions.eq("employee.employeeId", employeeId));
            Order order = (Order) criteria.uniqueResult();
            return order;
            
        } catch (Exception e) {
            throw new CustomException("Error while fetching orders by employee ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public boolean exist(Order order) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT COUNT(*) FROM Order WHERE orderId = :orderId");
            query.setParameter("orderId", order.getOrderId());
            Long count = (Long) query.uniqueResult();
            return count > 0;
        } catch (Exception e) {
            throw new CustomException("Error while checking if order exists: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public int updateOrderByOrderId(Order order) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            order = (Order) session.get(Order.class, order.getOrderId());
            session.update(order);
            transaction.commit();
            
            return order.getOrderId();
            
        } catch (Exception e) {
            throw new CustomException("Error while updating order by order ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public int updateOrderByShipperId(Date orderDate, int shipperId) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
    
            String hql = "UPDATE Order SET orderDate = (:orderDate) where shipper.id = :shipperId";
            session.createQuery(hql).setParameter("orderDate", orderDate).setParameter("shipperId", shipperId).executeUpdate();
            session.getTransaction();
            transaction.commit();
            
        } catch (Exception e) {
            throw new CustomException("Error while updating order by shipper ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
		return shipperId;
    }

    @Override
    public int deleteOrderByOrderId(int orderId) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Order orderToDelete = (Order) session.get(Order.class, orderId);
            orderToDelete.setIsDeleted(1);
            if (orderToDelete != null) {
                session.update(orderToDelete);
            }
            transaction.commit();
            return (orderToDelete != null) ? 1 : 0;
        } catch (Exception e) {
            throw new CustomException("Error while deleting order by ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public int deleteOrdersByEmployeeId(int employeeId) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE Order SET isDeleted = 1 WHERE employee.id = (:employeeId)";
            Query query = session.createQuery(hql);
            query.setParameter("employeeId", employeeId);
            int deletedCount = query.executeUpdate();
            transaction.commit();
            return deletedCount;
        } catch (Exception e) {
            throw new CustomException("Error while deleting orders by employee ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public int deleteOrdersByCustomerId(int customerId) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE Order SET isDeleted = 1 WHERE customer.id = (:customerId)";
            Query query = session.createQuery(hql);
            query.setParameter("customerId", customerId);
            int deletedCount = query.executeUpdate();
            transaction.commit();
            return deletedCount;
        } catch (Exception e) {
            throw new CustomException("Error while deleting orders by customer ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public int insertOrder(Order order) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            int orderId = (int) session.save(order);
            transaction.commit();
            return orderId;
        } catch (Exception e) {
            throw new CustomException("Error while inserting order: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public int insertMultipleOrders(List<Order> orders) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            for (Order order : orders) {
            	
            	session.save(order.getCustomer());
            	session.save(order.getEmployee());
            	session.save(order.getShipper());
                session.save(order);
            }
            transaction.commit();
            return orders.size();
        } catch (Exception e) {
            throw new CustomException("Error while inserting multiple orders: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
}
