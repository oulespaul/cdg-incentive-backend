package com.cdg.cdg_incentive_backend.module.department.service;

import com.cdg.cdg_incentive_backend.module.department.dto.response.DepartmentResponse;
import com.cdg.cdg_incentive_backend.module.department.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAll();
    List<DepartmentResponse> getAllResponse();
}
