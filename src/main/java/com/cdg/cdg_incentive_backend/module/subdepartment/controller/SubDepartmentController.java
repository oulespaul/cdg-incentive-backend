package com.cdg.cdg_incentive_backend.module.subdepartment.controller;

import com.cdg.cdg_incentive_backend.module.subdepartment.dto.request.ImportSubDepartmentRequest;
import com.cdg.cdg_incentive_backend.module.subdepartment.dto.response.SubDepartmentResponse;
import com.cdg.cdg_incentive_backend.module.subdepartment.service.SubDepartmentDataService;
import com.cdg.cdg_incentive_backend.module.subdepartment.service.SubDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sub-department")
public class SubDepartmentController {
    private final SubDepartmentService subDepartmentService;
    private final SubDepartmentDataService subDepartmentDataService;

    @GetMapping
    List<SubDepartmentResponse> getAll() {
        return subDepartmentService.getAllResponse();
    }

    @PostMapping("/import-data")
    ResponseEntity<Boolean> importSubDepartment(
            @RequestBody List<ImportSubDepartmentRequest> importSubDepartmentRequestList
    ) {
        subDepartmentDataService.importSubDepartment(importSubDepartmentRequestList);
        return ResponseEntity.ok(true);
    }
}
