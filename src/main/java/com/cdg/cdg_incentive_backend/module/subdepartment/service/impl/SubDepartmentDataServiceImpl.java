package com.cdg.cdg_incentive_backend.module.subdepartment.service.impl;

import com.cdg.cdg_incentive_backend.module.department.entity.Department;
import com.cdg.cdg_incentive_backend.module.department.service.DepartmentService;
import com.cdg.cdg_incentive_backend.module.subdepartment.dto.request.ImportSubDepartmentRequest;
import com.cdg.cdg_incentive_backend.module.subdepartment.entity.SubDepartment;
import com.cdg.cdg_incentive_backend.module.subdepartment.repository.SubDepartmentRepository;
import com.cdg.cdg_incentive_backend.module.subdepartment.service.SubDepartmentDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubDepartmentDataServiceImpl implements SubDepartmentDataService {
    private final SubDepartmentRepository subDepartmentRepository;
    private final DepartmentService departmentService;

    @Override
    public void importSubDepartment(List<ImportSubDepartmentRequest> importSubDepartmentRequestList) {
        List<Department> departmentList = departmentService.getAll();

        List<SubDepartment> subDepartmentList = new ArrayList<>();
        for (ImportSubDepartmentRequest importSubDepartmentRequest : importSubDepartmentRequestList) {
            Department departmentByCode = findDepartmentByCode(departmentList, importSubDepartmentRequest.getDepartmentCode());

            SubDepartment subDepartment = SubDepartment.builder()
                    .department(departmentByCode)
                    .subDepartmentCode(importSubDepartmentRequest.getSubDepartmentCode())
                    .subDepartmentName(importSubDepartmentRequest.getSubDepartmentName())
                    .build();

            subDepartmentList.add(subDepartment);
        }
        subDepartmentRepository.saveAll(subDepartmentList);
    }

    private Department findDepartmentByCode(List<Department> departmentList, String departmentCode) {
        return departmentList.stream()
                .filter(department -> department.getDepartmentCode().equals(departmentCode))
                .findFirst().orElseThrow(() -> new RuntimeException("Department code not found"));
    }
}
