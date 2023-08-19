package com.highradius.salesManagement.dao;

import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.Order;

import java.util.Date;
import java.util.List;

public interface OrderDao {

    List<Order> allOrders() throws CustomException;

    Order orderById(int orderId) throws CustomException;

    Order ordersByCustomerId(int customerId) throws CustomException;

    List<Order> ordersByOrderDate(Date orderDate) throws CustomException;

    Order ordersByEmployeeId(int employeeId) throws CustomException;
    
    
    // update methods

    int updateOrderByOrderId(Order order) throws CustomException;
	
	int updateOrderByShipperId(Date ordderDate, int shipperId) throws CustomException;
	
	
	// delete methods

    int deleteOrderByOrderId(int orderId) throws CustomException;

    int deleteOrdersByEmployeeId(int employeeId) throws CustomException;

    int deleteOrdersByCustomerId(int customerId) throws CustomException;
    
    // insert methods

    int insertOrder(Order order) throws CustomException;

    int insertMultipleOrders(List<Order> orders) throws CustomException;

	boolean exist(Order order) throws CustomException;

}
