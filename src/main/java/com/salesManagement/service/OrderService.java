package com.highradius.salesManagement.service;

import java.util.Date;
import java.util.List;

import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.Order;

public interface OrderService {

    List<Order> allOrders() throws CustomException;

    Order orderById(int orderId) throws CustomException;

    Order ordersByCustomerId(int customerId) throws CustomException;

    List<Order> ordersByOrderDate(Date orderDate) throws CustomException;

    Order ordersByEmployeeId(int employeeId) throws CustomException;
    
    // update methods

    int updateOrderByOrderId(Order order) throws CustomException;

    int updateOrderByShipperId(Date orderDate, int shipperId) throws CustomException;
    
    // delete methods

    int deleteOrderByOrderId(int orderId) throws CustomException;

    int deleteOrdersByEmployeeId(int employeeId) throws CustomException;

    int deleteOrdersByCustomerId(int customerId) throws CustomException;
    
    // insert methods

    int insertOrder(Order order) throws CustomException;

    int insertMultipleOrders(List<Order> orders) throws CustomException;
    
}
