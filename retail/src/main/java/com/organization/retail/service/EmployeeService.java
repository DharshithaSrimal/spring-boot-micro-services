package com.organization.retail.service;

import com.organization.retail.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService{
    @Autowired
    private RestTemplate restTemplate;

    private String employeeServiceUrl = "http://localhost:8085";

    public EmployeeDTO getEmployeeById(Integer empId) {
        try {
            String url = employeeServiceUrl + "/api/employees/" + empId;
            return restTemplate.getForObject(url, EmployeeDTO.class);
        } catch (RestClientException e) {
            // Log the error and return null or throw custom exception
            System.err.println("Error fetching employee with ID " + empId + ": " + e.getMessage());
            return null;
        }
    }


}
