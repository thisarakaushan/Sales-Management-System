package com.highradius.salesManagement.serviceImpl;

import java.util.Date;
import java.util.List;

import com.highradius.salesManagement.dao.OrderDao;
import com.highradius.salesManagement.daoImpl.OrderDaoImpl;
import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.Order;
import com.highradius.salesManagement.service.OrderService;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> allOrders() throws CustomException {
        try {
            return orderDao.allOrders();
        } catch (Exception e) {
            throw new CustomException("Error while fetching all orders: " + e.getMessage());
        }
    }

    @Override
    public Order orderById(int orderId) throws CustomException {
        try {
            return (Order) orderDao.orderById(orderId);
        } catch (Exception e) {
            throw new CustomException("Error while fetching order by ID: " + e.getMessage());
        }
    }

    @Override
    public Order ordersByCustomerId(int customerId) throws CustomException {
        try {
            return orderDao.ordersByCustomerId(customerId);
        } catch (Exception e) {
            throw new CustomException("Error while fetching orders by customer ID: " + e.getMessage());
        }
    }

    @Override
    public List<Order> ordersByOrderDate(Date orderDate) throws CustomException {
        try {
            return (List<Order>) orderDao.ordersByOrderDate(orderDate);
        } catch (Exception e) {
            throw new CustomException("Error while fetching orders by order date: " + e.getMessage());
        }
    }

    @Override
    public Order ordersByEmployeeId(int employeeId) throws CustomException {
        try {
            return orderDao.ordersByEmployeeId(employeeId);
        } catch (Exception e) {
            throw new CustomException("Error while fetching orders by employee ID: " + e.getMessage());
        }
    }

    @Override
    public int updateOrderByOrderId(Order order) throws CustomException {
        try {
            return orderDao.updateOrderByOrderId(order);
        } catch (Exception e) {
            throw new CustomException("Error while updating order by order ID: " + e.getMessage());
        }
    }

    @Override
    public int updateOrderByShipperId(Date orderDate, int shipperId) throws CustomException {
        try {
            return orderDao.updateOrderByShipperId(orderDate, shipperId);
        } catch (Exception e) {
            throw new CustomException("Error while updating order by shipper ID: " + e.getMessage());
        }
    }

    @Override
    public int deleteOrderByOrderId(int orderId) throws CustomException {
        try {
            return orderDao.deleteOrderByOrderId(orderId);
        } catch (Exception e) {
            throw new CustomException("Error while deleting order by order ID: " + e.getMessage());
        }
    }

    @Override
    public int deleteOrdersByEmployeeId(int employeeId) throws CustomException {
        try {
            return orderDao.deleteOrdersByEmployeeId(employeeId);
        } catch (Exception e) {
            throw new CustomException("Error while deleting orders by employee ID: " + e.getMessage());
        }
    }

    @Override
    public int deleteOrdersByCustomerId(int customerId) throws CustomException {
        try {
            return orderDao.deleteOrdersByCustomerId(customerId);
        } catch (Exception e) {
            throw new CustomException("Error while deleting orders by customer ID: " + e.getMessage());
        }
    }

    @Override
    public int insertOrder(Order order) throws CustomException {
        try {
            return orderDao.insertOrder(order);
        } catch (Exception e) {
            throw new CustomException("Error while inserting order: " + e.getMessage());
        }
    }

    @Override
    public int insertMultipleOrders(List<Order> orders) throws CustomException {
        try {
            return orderDao.insertMultipleOrders(orders);
        } catch (Exception e) {
            throw new CustomException("Error while inserting multiple orders: " + e.getMessage());
        }
    }
}
