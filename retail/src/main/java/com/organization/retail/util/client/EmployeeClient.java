package com.organization.retail.util.client;

import com.organization.retail.dto.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "employee-service", url = "http://localhost:8085")
@FeignClient(name = "employee-service", url = "${employee.service.url}")
public interface EmployeeClient {
    @GetMapping("/api/employees/{id}")
    EmployeeDTO getEmployeeById(@PathVariable("id") Long id);
}
