package com.cdg.cdg_incentive_backend.module.subdepartment.service.impl;

import com.cdg.cdg_incentive_backend.module.subdepartment.entity.SubDepartment;
import com.cdg.cdg_incentive_backend.module.subdepartment.repository.SubDepartmentRepository;
import com.cdg.cdg_incentive_backend.module.subdepartment.service.SubDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubDepartmentServiceImpl implements SubDepartmentService {
    private final SubDepartmentRepository subDepartmentRepository;

    @Override
    public List<SubDepartment> getAll() {
        return subDepartmentRepository.findAll();
    }
}
