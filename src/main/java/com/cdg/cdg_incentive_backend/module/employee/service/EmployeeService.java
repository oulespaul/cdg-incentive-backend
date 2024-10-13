package com.cdg.cdg_incentive_backend.module.employee.service;

import com.cdg.cdg_incentive_backend.dto.response.FilterResponse;
import com.cdg.cdg_incentive_backend.module.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    void saveAll(List<Employee> employeeList);

    Page<Employee> getAll(String year, String month, String employeeId, String branchCode, String branchBU, Pageable pageable);

    List<FilterResponse> getDistinctFilter(String fieldName);
}