package com.cdg.cdg_incentive_backend.module.department.service.impl;

import com.cdg.cdg_incentive_backend.module.department.dto.response.DepartmentResponse;
import com.cdg.cdg_incentive_backend.module.department.entity.Department;
import com.cdg.cdg_incentive_backend.module.department.mapper.DepartmentMapper;
import com.cdg.cdg_incentive_backend.module.department.repository.DepartmentRepository;
import com.cdg.cdg_incentive_backend.module.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public List<DepartmentResponse> getAllResponse() {
        return departmentMapper.fromEntityToDTOList(getAll());
    }

    @Override
    public Department getById(Integer id) {
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    public List<Department> getAllById(List<Integer> idList) {
        return departmentRepository.findAllById(idList);
    }
}
