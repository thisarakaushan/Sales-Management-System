package com.highradius.salesManagement.actions;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.highradius.salesManagement.exceptions.CustomException;
import com.highradius.salesManagement.pojo.Order;
import com.highradius.salesManagement.service.OrderService;

public class OrderActions {

    private Order order;
    public List<Order> orders;
    int orderId;
    int customerId;
    int employeeId;
    int shipperId;
    Date orderDate;

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getShipperId() {
		return shipperId;
	}

	public void setShipperId(int shipperId) {
		this.shipperId = shipperId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	private OrderService orderService;
    private ApplicationContext context;
    

    public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public OrderActions() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        orderService = (OrderService) context.getBean("orderServiceImpl");
    }

    // GET Operations

	/**
	 * Method for get all orders
	 * 
	 * @return
	 */
    public String allOrders() {
        try {
            orders = orderService.allOrders();
            if (orders != null) {
                return "success";
            } else {
            	
            	throw new CustomException("No order found.");
            }
            
        } catch (CustomException e) {
            throw new RuntimeException("Error fetching orders: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for get orders by Id
     * 
     * @return
     */
    public String orderById() {
        try {
            order = orderService.orderById(this.orderId);
            if (order != null) {
                return "success";
            } else {
            	
            	throw new CustomException("Order not found for ID: " + this.orderId);
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error fetching order: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for get order by orderDate
     * 
     * @return
     */
    public String ordersByOrderDate() {
        try {
            orders = orderService.ordersByOrderDate(orderDate);
            if (orders != null) {
                return "success";
            } else {
            	
            	throw new CustomException("Order not found for orderDate: " + this.orderDate);
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error fetching orders: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for get orders by customerID
     * 
     * @return
     */
    public String ordersByCustomerId() {
        try {
            order = orderService.ordersByCustomerId(this.customerId);
            if (order != null) {
                return "success";
            } else {
            	
            	throw new CustomException("Order not found for CustomerId: " + this.customerId);
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error fetching orders: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for get order by EmployeeId
     * 
     * @return
     */
    public String ordersByEmployeeId() {
        try {
            order = orderService.ordersByEmployeeId(this.employeeId);
            if (order != null) {
                return "success";
            } else {
            	
            	throw new CustomException("Order not found for EmployeeId: " + this.employeeId);
            }
        } catch (CustomException e) {
            throw new RuntimeException("Error fetching orders: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    // UPDATE Operations

    /**
     * Method for update order by id
     * 
     * @return
     */
    public String updateOrderByOrderId() {
        try {
            int result = orderService.updateOrderByOrderId(order);
            return (result > 0) ? "updated" : "error";
        } catch (CustomException e) {
            throw new RuntimeException("Error updating order: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for update order by shipperId
     * 
     * @return
     */
    public String updateOrderByShipperId() {
        try {
            int result = orderService.updateOrderByShipperId(orderDate, shipperId);
            return (result > 0) ? "updated" : "error";
        } catch (CustomException e) {
            throw new RuntimeException("Error updating order: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    // DELETE Operations

    /**
     * Method for delete order by orderId
     * 
     * @return
     */
    public String deleteOrderByOrderId() {
        try {
            int result = orderService.deleteOrderByOrderId(orderId);
            return (result > 0) ? "deleted" : "error";
        } catch (CustomException e) {
            throw new RuntimeException("Error deleting order: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for delete order by employeeID
     * 
     * @return
     */
    public String deleteOrdersByEmployeeId() {
        try {
            int result = orderService.deleteOrdersByEmployeeId(employeeId);
            return (result > 0) ? "deleted" : "error";
        } catch (CustomException e) {
            throw new RuntimeException("Error deleting orders: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for delete order by customerID
     * 
     * @return
     */
    public String deleteOrdersByCustomerId() {
        try {
            int result = orderService.deleteOrdersByCustomerId(customerId);
            return (result > 0) ? "deleted" : "error";
        } catch (CustomException e) {
            throw new RuntimeException("Error deleting orders: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    // INSERT Operations

    /**
     * Method for insert order
     * 
     * @return
     */
    public String insertOrder() {
        try {
            int result = orderService.insertOrder(order);
            return (result > 0) ? "added" : "error";
        } catch (CustomException e) {
            throw new RuntimeException("Error inserting order: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Method for insert multiple orders
     * 
     * @return
     */
    public String insertMultipleOrders() {
        try {
            int result = orderService.insertMultipleOrders(orders);
            return (result > 0) ? "added" : "error";
        } catch (CustomException e) {
            throw new RuntimeException("Error inserting orders: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }
}
