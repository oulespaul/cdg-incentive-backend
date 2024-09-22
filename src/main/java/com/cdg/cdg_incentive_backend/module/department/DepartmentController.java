package com.cdg.cdg_incentive_backend.module.department;

import com.cdg.cdg_incentive_backend.module.department.dto.response.DepartmentResponse;
import com.cdg.cdg_incentive_backend.module.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    List<DepartmentResponse> getAll() {
        return departmentService.getAllResponse();
    }
}
