package com.cdg.cdg_incentive_backend.module.targetbranch.controller;

import com.cdg.cdg_incentive_backend.module.targetbranch.dto.request.CreateTargetBranchRequest;
import com.cdg.cdg_incentive_backend.module.targetbranch.dto.request.MakeActionRequest;
import com.cdg.cdg_incentive_backend.module.targetbranch.dto.response.TargetBranchDetailResponse;
import com.cdg.cdg_incentive_backend.module.targetbranch.dto.response.TargetBranchResponse;
import com.cdg.cdg_incentive_backend.module.targetbranch.service.TargetBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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

    @GetMapping
    ResponseEntity<Page<TargetBranchDetailResponse>> getAll(
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "branchNumber", required = false) String branchNumber,
            @RequestParam(value = "branchBU", required = false) String branchBU,
            @RequestParam(value = "branchCode", required = false) String branchCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(targetBranchService.getAllDetail(year, month, status, branchNumber, branchBU, branchCode, pageable));
    }

    @GetMapping("target-commission/{target-commission-id}")
    ResponseEntity<TargetBranchResponse> getOneByTargetCommissionId(
            @PathVariable("target-commission-id") Integer targetCommissionId
    ) {
        return ResponseEntity.ok(targetBranchService.getOneByTargetCommissionId(targetCommissionId));
    }

    @DeleteMapping("{target-branch-id}")
    ResponseEntity<String> deleteById(
            @PathVariable("target-branch-id") Integer targetBranchId
    ) {
        targetBranchService.deleteById(targetBranchId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Target Branch deleted successfully.");
    }

    @GetMapping("{id}")
    ResponseEntity<TargetBranchResponse> getOneByTargetBranch(
            @PathVariable("id") Integer targetBranchId
    ) {
        return ResponseEntity.ok(targetBranchService.getDetailByTargetBranchId(targetBranchId));
    }

    @PutMapping("/make-action")
    ResponseEntity<String> makeTargetBranchAction(
            @RequestBody MakeActionRequest makeActionRequest,
            @AuthenticationPrincipal Jwt jwt
    ) {
        // TODO: Refactor
        String name = jwt.getClaimAsString("name");
        targetBranchService.makeAction(makeActionRequest, name);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Target Branch action successfully.");
    }
}
