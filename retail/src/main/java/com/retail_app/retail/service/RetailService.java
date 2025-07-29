package com.retail_app.retail.service;

import com.retail_app.retail.client.ProductClient;
import com.retail_app.retail.model.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetailService {
    private final ProductClient employeeClient;

    public RetailService(ProductClient employeeClient) {
        this.employeeClient = employeeClient;
    }

    public List<EmployeeDTO> fetchEmployeesFromEmployeeService() {
        return employeeClient.getAllEmployees();
    }

}
