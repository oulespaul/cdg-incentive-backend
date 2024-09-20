package com.cdg.cdg_incentive_backend.module.subdepartment.controller;

import com.cdg.cdg_incentive_backend.module.subdepartment.dto.response.SubDepartmentResponse;
import com.cdg.cdg_incentive_backend.module.subdepartment.service.SubDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sub-department")
public class SubDepartmentController {
    private final SubDepartmentService subDepartmentService;

    @GetMapping
    List<SubDepartmentResponse> getAll() {
        return subDepartmentService.getAllResponse();
    }
}
