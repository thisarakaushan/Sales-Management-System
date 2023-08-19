<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Employee Profile</title>
</head>
<body>
    <h1>Employee Profile</h1>
    <p>Employee ID: ${employee.employeeId}</p>
    <p>First Name: ${employee.firstName}</p>
    <p>Last Name: ${employee.lastName}</p>
    <p>Birth Date: ${employee.birthDate}</p>
    <!-- ... other employee details ... -->
    
    <h2>Employee Photo</h2>
    <img src="data:image/jpeg;base64,${employeePhoto}" alt="Employee Photo">
    
    <p><a href="employeeList">Back to Employee List</a></p>
</body>
</html>
