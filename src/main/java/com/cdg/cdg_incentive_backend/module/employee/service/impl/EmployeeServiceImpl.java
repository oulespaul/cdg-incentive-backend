package com.cdg.cdg_incentive_backend.module.employee.service.impl;

import com.cdg.cdg_incentive_backend.module.employee.dto.UpdateEmployeeRequest;
import com.cdg.cdg_incentive_backend.module.employee.entity.Employee;
import com.cdg.cdg_incentive_backend.module.employee.repository.EmployeeRepository;
import com.cdg.cdg_incentive_backend.module.employee.service.EmployeeService;
import com.cdg.cdg_incentive_backend.shared.dto.AppUserInfo;
import com.cdg.cdg_incentive_backend.shared.dto.response.FilterResponse;
import com.cdg.cdg_incentive_backend.shared.mapper.FilterMapper;
import com.cdg.cdg_incentive_backend.shared.service.impl.BaseSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl extends BaseSessionService implements EmployeeService {
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

    @Override
    public void duplicateEmployeeById(Integer id) {
        AppUserInfo sessionClaimsInfo = this.getAppUserSessionClaimsInfo();
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        Employee newEmployee = new Employee();
        BeanUtils.copyProperties(employee, newEmployee);
        newEmployee.setId(null);
        newEmployee.setCreatedAt(null);
        newEmployee.setCreatedBy(sessionClaimsInfo.getUsername());
        newEmployee.setUpdatedAt(null);
        newEmployee.setUpdatedBy(null);
        employeeRepository.save(newEmployee);
    }

    @Override
    public void updateEmployeeById(Integer id, UpdateEmployeeRequest employeeUpdateRequest) {
        AppUserInfo sessionClaimsInfo = this.getAppUserSessionClaimsInfo();
        Employee employeeToUpdate = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));

        employeeToUpdate.setEmployeeId(employeeUpdateRequest.getEmployeeId());
        employeeToUpdate.setNewCostCenter(employeeUpdateRequest.getNewCostCenter());
        employeeToUpdate.setEmployeeGroup(employeeUpdateRequest.getEmployeeGroup());
        employeeToUpdate.setBusinessUnit(employeeUpdateRequest.getBusinessUnit());
        employeeToUpdate.setPositionDescription(employeeUpdateRequest.getPositionDescription());
        employeeToUpdate.setScheme(employeeUpdateRequest.getScheme());
        employeeToUpdate.setDayWorking(employeeUpdateRequest.getDayWorking());
        employeeToUpdate.setCostCenter(employeeUpdateRequest.getCostCenter());
        employeeToUpdate.setBrandId(employeeUpdateRequest.getBrandId());
        employeeToUpdate.setCorporateTitle(employeeUpdateRequest.getCorporateTitle());
        employeeToUpdate.setBranchNo(employeeUpdateRequest.getBranchNo());
        employeeToUpdate.setHireDate(employeeUpdateRequest.getHireDate() != null ? LocalDate.parse(employeeUpdateRequest.getHireDate()) : null);
        employeeToUpdate.setBrandId(employeeUpdateRequest.getBrandId());
        employeeToUpdate.setTerminatedDate(employeeUpdateRequest.getTerminatedDate() != null ? LocalDate.parse(employeeUpdateRequest.getTerminatedDate()) : null);
        employeeToUpdate.setUpdatedAt(LocalDateTime.now());
        employeeToUpdate.setUpdatedBy(sessionClaimsInfo.getUsername());
        employeeRepository.save(employeeToUpdate);
    }
}
