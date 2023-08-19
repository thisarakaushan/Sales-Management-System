# Sales Management Web Application

Welcome to the Sales Management Web Application! This project is designed to manage customer, employee, order, and order
details information. It utilizes the Spring framework for dependency injection and Struts for web interactions. Hibernate 
is used for database operations, and the application is built using Java.

## Getting Started

Follow these steps to set up and run the project on your local machine:

### Prerequisites

- Java JDK (version 8)
- [Apache Tomcat]() (version 8.5)
- MySQL database
- [Struts](http://www.java2s.com/Code/Jar/s/Downloadstrutscore1310jar.htm#google_vignette)
- [Hibernate](https://jar-download.com/download-handling.php)
- [Spring](http://www.java2s.com/example/jar/s/download-springboot153releasejar-file.html) Framework

### Requirments

1. Create a Dynamic web application using spring, Struts and Hibernate using the below details about POJO classes.
 
2. Use Appropriate response codes for all request(s) and make sure to use JSON as request and response body.

```
Customers:
    customer_id
    customer_name
    contact_name
    address
    city
    postal_code
    country

Categories:
    category_id
    category_name
    description

Employees:
    employee_id
    last_name
    first_name
    birth_date
    photo
    notes

OrderDetails:
    order_detail_id
    order_id
    product_id
    quantity

Orders:
    order_id
    customer_id
    employee_id
    order_date
    shipper_id

Products:
    product_id
    product_name
    supplier_id
    category_id
    unit
    price

Shippers:
    shipper_id
    shipper_name
    phone

Suppliers:
    supplier_id
    supplier_name
    contact_name
    address
    city
    postal_code
    country
    phone
```

3. Create an REST API to get the different routes for accessing the details
```   
1.ORDERS
    a.GET Order(s)
        i.All orders
        ii.Order By order_id
        iii.Order(s) by customer_id
        iv.Order(s) by order_date
        v.Order(s) by employee_id

    b.UPDATE Order(s)
        i.By order_id with appropriate details
        ii.By shipper_id

    c.DELETE Order(s)
        i.Delete and order by order_id
        ii.Delete order(s) using an employee_id
        iii.Delete order(s) using customer_id

    d.INSERT Order(s) <br>
        i.Insert single order using appropriate fields
        ii.Insert multiple order(s)

2.CUSTOMERS
    a.GET Customer
        i.All Customers
        ii.By customer_id
        iii.By country(s)

    b.UPDATE Customer
        i.By customer_id and appropriate details

    c.DELETE Customer
        i.By Customer_id(s)
        ii.By country name

    d.INSERT Customer
        i.Single customer
        ii.Multiple customer(s)

3.Employees
    a.GET Employees
        i.All Employee(s)
        ii.By employee_id

    b.UPDATE Employees
        i.By employee_id

    c.DELETE Employees
        i.By employee_id

    d.INSERT Employees
        i.Single employee
        ii.Multiple employee(s)
        
4.Order Details
    a.GET Order Details
        i.By order_detail_id
        ii.By order_detail_id(s)

    b.UPDATE Order Details
        i.By order_detail_id and appropriate details
        ii.By product_id and appropriate details
        iii.By order_id and appropriate details

    c.DELETE Order Details
        i. By order_id
        ii.By order_id(s)

    d.INSERT Order Details
        i.Order details by using appropriate details<br>
```

### Installation

1. Clone the repository to your local machine:

```
   git clone https://github.com/thisarakaushan/Sales-Management-System.git
```

2. Open the project in your preferred Java development environment (e.g., Eclipse, IntelliJ).

3. Map the methods and control the user request from `struts.xml` file.

4. Configure your database connection details in the `applicationContext.xml` and  `hibernate.cfg.xml` files.

5. Build and deploy the project on your Apache Tomcat server.

6. Access the application by navigating to `http://localhost:8080/Sales-Management-System` in your web browser.

### Features  

 - View, add, update, and delete customer records.
 - View, add, update, and delete employee records.
 - View, add, update, and delete order records.
 - View, add, update, and delete order detail records.

### Directory Structure

The project follows a standard directory structure:

* `src/main/java`: Contains Java source code.
  - `com.highradius.salesManagement.actions`: Contains Struts action classes.
  - `com.highradius.salesManagement.dao`: Contains DAO interfaces.
  - `com.highradius.salesManagement.daoImpl`: Contains DAO implementations.
  - `com.highradius.salesManagement.exceptions`: Contains custom exception classes.
  - `com.highradius.salesManagement.pojo`: Contains Java POJO classes.
  - `com.highradius.salesManagement.service`: Contains service interfaces.
  - `com.highradius.salesManagement.serviceImpl`: Contains service implementations.
    
* `src/main/webapp`: Contains web resources.
  - `WEB-INF`: Contains configuration files and JSP views.

```
src/
|-- main/
|   |-- java/
|   |   |-- com/
|   |	|     |-- highradius/
|   |  	|     |-- salesManagement/
|   |   |           |-- actions/
|   |   |           |   |-- CustomerActions.java
|   |   |           |   |-- OrderActions.java
|   |   |           |   |-- EmployeeActions.java
|   |   |           |   |-- OrderDetailActions.java
|   |   |           |
|   |   |           |-- dao/
|   |   |           |   |-- CustomerDao.java
|   |   |           |   |-- OrderDao.java
|   |   |           |   |-- EmployeeDao.java
|   |   |           |   |-- OrderDetailDao.java
|   |   |           |
|   |   |           |-- daoImpl/
|   |   |           |   |-- CustomerDaoImpl.java
|   |   |           |   |-- OrderDaoImpl.java
|   |   |           |   |-- EmployeeDaoImpl.java
|   |   |           |   |-- OrderDetailDaoImpl.java
|   |   |           |
|   |   |           |-- service/
|   |   |           |   |-- CustomerService.java
|   |   |           |   |-- OrderService.java
|   |   |           |   |-- EmployeeService.java
|   |   |           |   |-- OrderDetailService.java
|   |   |           |   
|   |   |           |-- serviceImpl/
|   |   |           |   |-- CustomerServiceImpl.java
|   |   |           |   |-- OrderServiceImpl.java
|   |   |           |   |-- EmployeeServiceImpl.java
|   |   |           |   |-- OrderDetailServiceImpl.java
|   |   |           |
|   |   |           |-- pojo/
|   |   |           |   |-- Customer.java
|   |   |           |   |-- Product.java
|   |   |           |   |-- Order.java
|   |   |           |   |-- Employee.java
|   |   |           |   |-- Category.java
|   |   |           |   |-- Shipper.java
|   |   |           |   |-- Supplier.java
|   |   |           |   |-- OrderDetail.java
|   |	|	    |
|   |   |
|   |	|
|   |	|-- applicationContext.xml
|   |	|-- hibernate.cfg.xml
|   |	|-- struts.xml
|   |
|   |-- webapp/
|       |-- WEB-INF/
|       |    |-- web.xml/
|       |        
|	|-- employeeProfile.jsp
|       |-- error.jsp
|       |-- index.jsp
|       |-- success.jsp
|             
```

### Contributing

Contributions are welcome! Feel free to submit pull requests for bug fixes, improvements, or new features.
