package com.cdg.cdg_incentive_backend.module.subdepartment.service.impl;

import com.cdg.cdg_incentive_backend.module.subdepartment.dto.response.SubDepartmentResponse;
import com.cdg.cdg_incentive_backend.module.subdepartment.entity.SubDepartment;
import com.cdg.cdg_incentive_backend.module.subdepartment.mapper.SubDepartmentMapper;
import com.cdg.cdg_incentive_backend.module.subdepartment.repository.SubDepartmentRepository;
import com.cdg.cdg_incentive_backend.module.subdepartment.service.SubDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubDepartmentServiceImpl implements SubDepartmentService {
    private final SubDepartmentRepository subDepartmentRepository;
    private final SubDepartmentMapper subDepartmentMapper;

    @Override
    public List<SubDepartment> getAll() {
        return subDepartmentRepository.findAll();
    }

    @Override
    public List<SubDepartmentResponse> getAllResponse() {
        return subDepartmentMapper.fromEntityToDTOList(subDepartmentRepository.findAll());
    }

    @Override
    public SubDepartment getOneById(Integer id) {
        return subDepartmentRepository.findById(id).orElseThrow(() -> new RuntimeException("SubDepartment not found"));
    }
}
