package com.cdg.cdg_incentive_backend.module.employee.service.impl;

import com.cdg.cdg_incentive_backend.module.employee.entity.Employee;
import com.cdg.cdg_incentive_backend.module.employee.repository.EmployeeRepository;
import com.cdg.cdg_incentive_backend.module.employee.service.EmployeeService;
import com.cdg.cdg_incentive_backend.shared.dto.response.FilterResponse;
import com.cdg.cdg_incentive_backend.shared.mapper.FilterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final FilterMapper filterMapper;

    @Override
    public void saveAll(List<Employee> employeeList) {
        employeeRepository.saveAll(employeeList);
    }

    @Override
    public Page<Employee> getAll(String year, String month, String employeeId, String branchCode, String branchBU, Pageable pageable) {
        return employeeRepository.findAllResponse(year, month, employeeId, branchCode, branchBU, pageable);
    }

    @Override
    public List<FilterResponse> getDistinctFilter(String fieldName) {
        List<String> distinctValues = switch (fieldName) {
            case "year" -> employeeRepository.findDistinctYear();
            case "month" -> employeeRepository.findDistinctMonth();
            default -> throw new RuntimeException("Invalid field name");
        };
        return filterMapper.stringValueToDTOList(distinctValues);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }
}
