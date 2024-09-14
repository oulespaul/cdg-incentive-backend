package com.cdg.cdg_incentive_backend.module.department.service.impl;

import com.cdg.cdg_incentive_backend.module.department.entity.Department;
import com.cdg.cdg_incentive_backend.module.department.repository.DepartmentRepository;
import com.cdg.cdg_incentive_backend.module.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }
}
