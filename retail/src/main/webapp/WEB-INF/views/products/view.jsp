<!-- ============================================
FILE 4: view.jsp
Location: src/main/webapp/WEB-INF/views/products/view.jsp
============================================ -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Product</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            margin-bottom: 30px;
        }
        .detail-row {
            display: flex;
            padding: 15px;
            border-bottom: 1px solid #eee;
        }
        .detail-row:last-child {
            border-bottom: none;
        }
        .detail-label {
            font-weight: bold;
            color: #555;
            width: 200px;
            flex-shrink: 0;
        }
        .detail-value {
            color: #333;
        }
        .btn {
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
            margin: 5px;
        }
        .btn-primary {
            background-color: #007bff;
            color: white;
        }
        .btn-warning {
            background-color: #ffc107;
            color: black;
        }
        .btn-danger {
            background-color: #dc3545;
            color: white;
        }
        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }
        .btn-group {
            margin-top: 30px;
        }
        .employee-info {
            background-color: #f8f9fa;
            padding: 15px;
            border-radius: 5px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Product Details</h1>

    <div class="detail-row">
        <div class="detail-label">Product ID:</div>
        <div class="detail-value">${product.id}</div>
    </div>

    <div class="detail-row">
        <div class="detail-label">Product Name:</div>
        <div class="detail-value">${product.name}</div>
    </div>

    <div class="detail-row">
        <div class="detail-label">Description:</div>
        <div class="detail-value">${product.description}</div>
    </div>

    <div class="detail-row">
        <div class="detail-label">Price:</div>
        <div class="detail-value">$${product.price}</div>
    </div>

    <div class="detail-row">
        <div class="detail-label">Updated By:</div>
        <div class="detail-value">
            <c:choose>
                <c:when test="${product.employee != null}">
                    <div class="employee-info">
                        <strong>Employee Name:</strong> ${product.employee.firstName} ${product.employee.lastName}<br>
                        <strong>Employee ID:</strong> ${product.employee.id}<br>
                        <strong>Email:</strong> ${product.employee.email}
                    </div>
                </c:when>
                <c:otherwise>
                    N/A
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="btn-group">
        <a href="${pageContext.request.contextPath}/products" class="btn btn-secondary">Back to List</a>
        <a href="${pageContext.request.contextPath}/products/edit/${product.id}" class="btn btn-warning">Edit</a>
        <a href="${pageContext.request.contextPath}/products/delete/${product.id}"
           class="btn btn-danger"
           onclick="return confirm('Are you sure you want to delete this product?')">Delete</a>
    </div>
</div>
</body>
</html>