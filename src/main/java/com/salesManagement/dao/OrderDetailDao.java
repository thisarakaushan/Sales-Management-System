package com.highradius.salesManagement.dao;

import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.OrderDetail;

import java.util.List;

public interface OrderDetailDao {
	
	OrderDetail orderDetailById(int orderDetailId) throws CustomException;

	List<OrderDetail> orderDetailsByIds(List<Integer> orderDetailIds) throws CustomException;
	
	// update methods

    int updateOrderDetailById(OrderDetail orderDetail) throws CustomException;

    int updateOrderDetailByProductId(int productId, int quantity) throws CustomException;

    int updateOrderDetailByOrderId(int orderId, int quantity) throws CustomException;
    
    // delete methods

    int deleteOrderDetailsByOrderId(int orderId) throws CustomException;

    int deleteOrderDetailsByOrderIds(List<Integer> orderDetailIds) throws CustomException;
    
    // insert methods

    int insertOrderDetail(List<OrderDetail> orderDetails) throws CustomException;

}
