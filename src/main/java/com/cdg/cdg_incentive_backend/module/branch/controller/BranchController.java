package com.cdg.cdg_incentive_backend.module.branch.controller;

import com.cdg.cdg_incentive_backend.module.branch.entity.Branch;
import com.cdg.cdg_incentive_backend.module.branch.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/branch")
public class BranchController {
    private final BranchService branchService;

    @GetMapping
    ResponseEntity<List<Branch>> getAllBranch() {
        return ResponseEntity.ok(branchService.getAll());
    }
}
