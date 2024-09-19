package com.cdg.cdg_incentive_backend.module.targetbranch.controller;

import com.cdg.cdg_incentive_backend.module.targetbranch.dto.request.CreateTargetBranchRequest;
import com.cdg.cdg_incentive_backend.module.targetbranch.dto.response.TargetBranchResponse;
import com.cdg.cdg_incentive_backend.module.targetbranch.service.TargetBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/target-branch")
public class TargetBranchController {
    private final TargetBranchService targetBranchService;

    @PostMapping
    ResponseEntity<String> createTargetBranch(
            @RequestBody CreateTargetBranchRequest createTargetBranchRequest
    ) {
        targetBranchService.createTargetBranch(createTargetBranchRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Target Branch created successfully.");
    }

    @GetMapping("target-commission/{target-commission-id}")
    ResponseEntity<TargetBranchResponse> getOneByTargetCommissionId(
            @PathVariable("target-commission-id") Integer targetCommissionId
    ) {
        return ResponseEntity.ok(targetBranchService.getOneByTargetCommissionId(targetCommissionId));
    }
}
