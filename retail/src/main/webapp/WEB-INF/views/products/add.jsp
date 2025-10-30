<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Product</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 600px;
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
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }
        textarea {
            resize: vertical;
            min-height: 100px;
        }
        .btn {
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
            margin: 5px;
            border: none;
            cursor: pointer;
            font-size: 14px;
        }
        .btn-primary {
            background-color: #007bff;
            color: white;
        }
        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }
        .btn-group {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Add New Product</h1>

    <form action="${pageContext.request.contextPath}/products/add" method="post">
        <div class="form-group">
            <label for="name">Product Name *</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" name="description"></textarea>
        </div>

        <div class="form-group">
            <label for="price">Price *</label>
            <input type="number" id="price" name="price" step="0.01" min="0" required>
        </div>

        <div class="form-group">
            <label for="updatedBy">Updated By (Employee ID)</label>
            <input type="number" id="updatedBy" name="updatedBy">
        </div>

        <div class="btn-group">
            <button type="submit" class="btn btn-primary">Save Product</button>
            <a href="${pageContext.request.contextPath}/products" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>