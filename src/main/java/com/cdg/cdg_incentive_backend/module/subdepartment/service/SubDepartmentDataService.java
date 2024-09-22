package com.cdg.cdg_incentive_backend.module.subdepartment.service;

import com.cdg.cdg_incentive_backend.module.subdepartment.dto.request.ImportSubDepartmentRequest;

import java.util.List;

public interface SubDepartmentDataService {

    void importSubDepartment(List<ImportSubDepartmentRequest> importSubDepartmentRequestList);
}
