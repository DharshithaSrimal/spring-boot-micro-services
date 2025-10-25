package com.organization.retail.service;

import com.organization.retail.dto.EmployeeDTO;
import com.organization.retail.util.client.EmployeeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmployeeClient employeeFeignClient;

    @Value("${employee.service.api.key}")
    private String apiKey;
    private String employeeServiceUrl = "http://localhost:8085";

    public EmployeeDTO getEmployeeById(Integer empId) {
        try {
            String url = employeeServiceUrl + "/api/employees/" + empId;
            // Create headers with API key
            HttpHeaders headers = new HttpHeaders();
            headers.set("apiKey", apiKey);
            // Create HTTP entity with headers
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Make the request with headers
            ResponseEntity<EmployeeDTO> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    EmployeeDTO.class
            );

            return response.getBody();
//            return restTemplate.getForObject(url, EmployeeDTO.class);

        } catch (RestClientException e) {
            // Log the error and return null or throw custom exception
            System.err.println("Error fetching employee with ID " + empId + ": " + e.getMessage());
            return null;
        }
    }

    public EmployeeDTO getEmployee(Long id) {
        try {
            return employeeFeignClient.getEmployeeById(id);
        } catch (Exception e) {
            // Handle exceptions (network issues, 404, etc.)
            System.err.println("Error fetching employee: " + e.getMessage());
            throw e;
        }
    }


}
