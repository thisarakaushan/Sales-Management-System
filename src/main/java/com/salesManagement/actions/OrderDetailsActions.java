package com.highradius.salesManagement.actions;

import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.OrderDetail;
import com.highradius.salesManagement.service.OrderDetailService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class OrderDetailsActions {
	
	private OrderDetail orderDetail;
    private List<OrderDetail> details;
    private List<Integer> detailsIds;
    int detailId;
    int productId;
    int orderId;  
    int quantity;
    
	private OrderDetailService orderDetailService;
    private ApplicationContext context;

    public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public List<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

	public List<Integer> getDetailsIds() {
		return detailsIds;
	}

	public void setDetailsIds(List<Integer> detailsIds) {
		this.detailsIds = detailsIds;
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderDetailService getOrderDetailService() {
        return orderDetailService;
    }

    public void setOrderDetailService(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    public OrderDetailsActions() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        orderDetailService = (OrderDetailService) context.getBean("orderDetailServiceImpl");
    }

    // GET Order Details 
    
    /**
     * Method for get orderDetails by Id
     * 
     * @return
     */
    public String orderDetailById() {
        try {
            orderDetail = orderDetailService.orderDetailById(detailId);
            if (orderDetail != null) {
                return "success";
            } else {
            	
            	throw new CustomException("OrderDEtail not found for Id: " + this.detailId);
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error fetching order detail: " + e.getMessage());
        } catch (Exception e) {
        	throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    
    /**
     * Method for get orderDetails by Ids
     * 
     * @return
     */
    public String orderDetailsByIds() {
        try {
            details = orderDetailService.orderDetailsByIds(detailsIds);
            if (detailsIds != null && !detailsIds.isEmpty()) {
                return "success";
            } else {
            	
            	throw new CustomException("OrderDetail not found for Ids: " + this.detailsIds);
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error fetching order details: " + e.getMessage());
        } catch (Exception e) {
        	throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    // UPDATE Order Details 
    
    /**
     * Method for update orderDetails by Id
     * 
     * @return
     */
    public String updateOrderDetailById() {
        try {
            int result = orderDetailService.updateOrderDetailById(orderDetail);
            if (result > 0) {
                return "updated";
            } else {
            	
            	throw new CustomException("OrderDetail not found for Id: " + this.detailId);
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error updating order detail: " + e.getMessage());
        } catch (Exception e) {
        	throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for update orderDetails by ProductId
     * 
     * @return
     */
    public String updateOrderDetailByProductId() {
        try {
            int result = orderDetailService.updateOrderDetailByProductId(productId, quantity);
            if (result > 0) {
                return "updated";
            } else {
            	
            	throw new CustomException("OrderDetail not found for ProductId: " + this.productId);
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error updating order detail: " + e.getMessage());
        } catch (Exception e) {
        	throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for update orderDetails by OrderID
     * 
     * @return
     */
    public String updateOrderDetailByOrderId() {
        try {
            int result = orderDetailService.updateOrderDetailByOrderId(orderId, quantity);
            if (result > 0) {
                return "updated";
            } else {
            	
            	throw new CustomException("OrderDetail not found for CustomerId: " + this.orderId);
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error updating order detail: " + e.getMessage());
        } catch (Exception e) {
        	throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    // DELETE Order Details 

    /**
     * Method for delete orderDetails by OrderId
     * 
     * @return
     */
    public String deleteOrderDetailsByOrderId() {
        try {
            int result = orderDetailService.deleteOrderDetailsByOrderId(orderId);
            if (result > 0) {
                return "deleted";
            } else {
            	
            	throw new CustomException("OrderDetail not found for OrderId: " + this.orderId);
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error deleting order details: " + e.getMessage());
        } catch (Exception e) {
        	throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for delete orderDetails by OrderIds
     * 
     * @return
     */
    public String deleteOrderDetailsByOrderIds() {
        try {
            int result = orderDetailService.deleteOrderDetailsByOrderIds(detailsIds);
            if (result > 0) {
                return "deleted";
            } else {
            	
            	throw new CustomException("OrderDetail not found for Ids: " + this.detailsIds);
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error deleting order details: " + e.getMessage());
        } catch (Exception e) {
        	throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    // INSERT Order Details using appropriate details
    
    /**
     * Method for insert orderDetails
     * 
     * @return
     */
    public String insertOrderDetail() {
        try {
            int result = orderDetailService.insertOrderDetail(details);
            if (result > 0) {
                return "added";
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error inserting order detail: " + e.getMessage());
        } catch (Exception e) {
        	throw new RuntimeException("Error occurred: " + e.getMessage());
        }
        return "error";
    }
}
