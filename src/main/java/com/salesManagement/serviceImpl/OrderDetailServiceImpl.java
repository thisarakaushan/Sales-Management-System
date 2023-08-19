package com.highradius.salesManagement.serviceImpl;

import java.util.List;

import com.highradius.salesManagement.dao.OrderDetailDao;
import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.OrderDetail;
import com.highradius.salesManagement.service.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService {

    private OrderDetailDao orderDetailDao;

    public OrderDetailDao getOrderDetailDao() {
        return orderDetailDao;
    }

    public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
        this.orderDetailDao = orderDetailDao;
    }

    @Override
    public OrderDetail orderDetailById(int orderDetailId) throws CustomException {
        try {
            return (OrderDetail) orderDetailDao.orderDetailById(orderDetailId);
        } catch (Exception e) {
            throw new CustomException("Error while fetching order detail by ID: " + e.getMessage());
        }
    }

    @Override
    public List<OrderDetail> orderDetailsByIds(List<Integer> orderDetailIds) throws CustomException {
        try {
            return orderDetailDao.orderDetailsByIds(orderDetailIds);
        } catch (Exception e) {
            throw new CustomException("Error while fetching order details by order detail IDs: " + e.getMessage());
        }
    }

    @Override
    public int updateOrderDetailById(OrderDetail orderDetail) throws CustomException {
        try {
            return orderDetailDao.updateOrderDetailById(orderDetail);
        } catch (Exception e) {
            throw new CustomException("Error while updating order detail by ID: " + e.getMessage());
        }
    }

    @Override
    public int updateOrderDetailByProductId(int productId, int quantity) throws CustomException {
        try {
            return orderDetailDao.updateOrderDetailByProductId(productId, quantity);
        } catch (Exception e) {
            throw new CustomException("Error while updating order detail by product ID: " + e.getMessage());
        }
    }

    @Override
    public int updateOrderDetailByOrderId(int orderId, int quantity) throws CustomException {
        try {
            return orderDetailDao.updateOrderDetailByOrderId(orderId, quantity);
        } catch (Exception e) {
            throw new CustomException("Error while updating order detail by order detail ID: " + e.getMessage());
        }
    }

    @Override
    public int deleteOrderDetailsByOrderId(int orderId) throws CustomException {
        try {
            return orderDetailDao.deleteOrderDetailsByOrderId(orderId);
        } catch (Exception e) {
            throw new CustomException("Error while deleting order details by order ID: " + e.getMessage());
        }
    }

    @Override
    public int deleteOrderDetailsByOrderIds(List<Integer> orderDetailIds) throws CustomException {
        try {
            return orderDetailDao.deleteOrderDetailsByOrderIds(orderDetailIds);
        } catch (Exception e) {
            throw new CustomException("Error while deleting order details by order IDs: " + e.getMessage());
        }
    }

    @Override
    public int insertOrderDetail(List<OrderDetail> orderDetails) throws CustomException {
        try {
            return orderDetailDao.insertOrderDetail(orderDetails);
        } catch (Exception e) {
            throw new CustomException("Error while inserting order detail: " + e.getMessage());
        }
    }
}
