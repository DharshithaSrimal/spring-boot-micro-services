package com.retail_app.retail.client;

import com.retail_app.retail.model.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "employee-client", url = "http://localhost:8085")
public interface ProductClient {
    @GetMapping("api/employees")
    List<EmployeeDTO> getAllEmployees();

    @GetMapping("/{id}")
    EmployeeDTO getEmployeeById(@PathVariable Long id);
}
