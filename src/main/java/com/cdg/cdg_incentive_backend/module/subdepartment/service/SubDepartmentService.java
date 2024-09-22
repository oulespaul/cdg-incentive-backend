package com.cdg.cdg_incentive_backend.module.subdepartment.service;

import com.cdg.cdg_incentive_backend.module.subdepartment.dto.response.SubDepartmentResponse;
import com.cdg.cdg_incentive_backend.module.subdepartment.entity.SubDepartment;

import java.util.List;

public interface SubDepartmentService {
    List<SubDepartment> getAll();

    List<SubDepartmentResponse> getAllResponse(Integer departmentId);

    SubDepartment getOneById(Integer id);
}
