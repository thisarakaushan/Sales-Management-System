package com.highradius.salesManagement.daoImpl;

import com.highradius.salesManagement.dao.OrderDetailDao;
import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.OrderDetail;
import com.highradius.salesManagement.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {

	@Override
    public OrderDetail orderDetailById(int orderDetailId) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return (OrderDetail) session.get(OrderDetail.class, orderDetailId);
        } catch (Exception e) {
            throw new CustomException("Error while fetching order detail by ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<OrderDetail> orderDetailsByIds(List<Integer> orderDetailIds) throws CustomException {
        Session session = null;
        List<OrderDetail> orderDetails = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            Criteria criteria  = session.createCriteria(OrderDetail.class);
            criteria .add(Restrictions.in("orderDetailId", orderDetailIds));
			orderDetails = criteria.list();
			
			return orderDetails;
			
        } catch (Exception e) {
            throw new CustomException("Error while fetching order details by order detail IDs: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public int updateOrderDetailById(OrderDetail orderDetail) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            orderDetail = (OrderDetail) session.get(OrderDetail.class, orderDetail.getOrderDetailId());
            if (orderDetail != null) {
            	
                // Update fields of the orderDetail object
            	
            	orderDetail.setQuantity(orderDetail.getQuantity());
                //orderDetail.setProduct(orderDetail.getProduct());
                //orderDetail.setUnitPrice(orderDetail.getUnitPrice());
                // Set other fields as needed
                session.update(orderDetail);
            }
            transaction.commit();
            return (orderDetail != null) ? orderDetail.getOrderDetailId() : 0;
            
        } catch (Exception e) {
            throw new CustomException("Error while updating order detail by ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public int updateOrderDetailByProductId(int productId, int quantity) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE OrderDetail SET quantity = (:quantity) WHERE product.id = (:productId)";
            session.createQuery(hql).setParameter("quantity", quantity).setParameter("productId", productId).executeUpdate();
            session.getTransaction();
            transaction.commit();
            
        } catch (Exception e) {
            throw new CustomException("Error while updating order detail by product ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
		return quantity;
    }

    @Override
    public int updateOrderDetailByOrderId(int orderId, int quantity) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE OrderDetail SET quantity = (:quantity) WHERE order.id = (:orderId)";
            session.createQuery(hql).setParameter("quantity", quantity).setParameter("orderId", orderId).executeUpdate();
            session.getTransaction();
            transaction.commit();

        } catch (Exception e) {
            throw new CustomException("Error while updating order detail by order detail ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
		return quantity;
    }
    
    @Override
    public int deleteOrderDetailsByOrderId(int orderId) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE orderDetail SET isDeleted = 1 WHERE order.id = (:orderId)";
            int deletedRows = session.createQuery(hql)
                    .setParameter("orderId", orderId)
                    .executeUpdate();
            transaction.commit();
            return deletedRows;
        } catch (Exception e) {
            throw new CustomException("Error while deleting order details by order ID: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public int deleteOrderDetailsByOrderIds(List<Integer> orderDetailIds) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            for(int id: orderDetailIds) {
            	
            	OrderDetail detail = (OrderDetail) session.get(OrderDetail.class, id);
            	detail.setIsDeleted(1);
            	session.update(detail);
            }
           transaction.commit();

        } catch (Exception e) {
            throw new CustomException("Error while deleting order details by order IDs: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
		return 0;
    }

    @Override
    public int insertOrderDetail(List<OrderDetail> orderDetails) throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            for(OrderDetail detail: orderDetails) {
            	
            	session.save(detail.getOrder());
//            	session.save(detail.getOrder().getCustomer());
//            	session.save(detail.getOrder().getEmployee());
//            	session.save(detail.getOrder().getShipper());
            	session.save(detail.getProduct());
//            	session.save(detail.getProduct().getCategory());
//            	session.save(detail.getProduct().getSupplier());
            	session.save(detail);
            }
            transaction.commit();
            
        } catch (Exception e) {
            throw new CustomException("Error while inserting order detail: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
		return 0;
    }
}
