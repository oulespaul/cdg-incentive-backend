package com.cdg.cdg_incentive_backend.module.targetcommission.controller;

import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionFilterResponse;
import com.cdg.cdg_incentive_backend.module.targetcommission.dto.response.TargetCommissionResponse;
import com.cdg.cdg_incentive_backend.module.targetcommission.entity.TargetCommission;
import com.cdg.cdg_incentive_backend.module.targetcommission.mapper.TargetCommissionResponseMapper;
import com.cdg.cdg_incentive_backend.module.targetcommission.service.TargetCommissionService;
import com.cdg.cdg_incentive_backend.module.targetcommission.service.impl.TargetCommissionExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/target-commission")
public class TargetCommissionController {
    private final TargetCommissionService targetCommissionService;
    private final TargetCommissionExcelService targetCommissionExcelService;
    private final TargetCommissionResponseMapper targetCommissionResponseMapper;

    private static final List<String> VALID_EXTENSIONS = Arrays.asList("xlsx", "xls");

    @GetMapping
    public ResponseEntity<Page<TargetCommissionResponse>> getTargetCommission(
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "branchNumber", required = false) String branchNumber,
            @RequestParam(value = "branchBU", required = false) String branchBU,
            @RequestParam(value = "branchCode", required = false) String branchCode,
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize
    ) {
        return ResponseEntity.ok(targetCommissionService.getAllResponse(
                year,
                month,
                branchNumber,
                branchBU,
                branchCode,
                page,
                pageSize));
    }

    @GetMapping("/filter/year")
    public ResponseEntity<List<TargetCommissionFilterResponse>> getFilterYearTargetCommission(
            @RequestParam(value = "branchId", required = false) Integer branchId
    ) {
        return ResponseEntity.ok(targetCommissionService.getDistinctYearByBranchId(branchId));
    }

    @GetMapping("/filter/month")
    public ResponseEntity<List<TargetCommissionFilterResponse>> getFilterMonthTargetCommission(
            @RequestParam(value = "branchId", required = false) Integer branchId
    ) {
        return ResponseEntity.ok(targetCommissionService.getDistinctMonthByBranchId(branchId));
    }

    @GetMapping("/filter/branch")
    public ResponseEntity<List<TargetCommissionFilterResponse>> getFilterBranchNumberTargetCommission() {
        return ResponseEntity.ok(targetCommissionService.getDistinctBranchNumber());
    }

    @GetMapping("/detail")
    public ResponseEntity<TargetCommissionResponse> getOneByCriteria(
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "branchId", required = false) Integer branchId
    ) {
        return ResponseEntity.ok(targetCommissionService.getOneByYearAndMonthAndBranchId(year, month, branchId));
    }

    @PostMapping("/upload/validate")
    public ResponseEntity<?> validateFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            if (filename == null || !hasValidExtension(filename)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file extension");
            }

            List<TargetCommission> data = targetCommissionExcelService.parseExcelFile(file);
            List<String> errors = targetCommissionExcelService.validateData(data);

            if (!errors.isEmpty()) {
                return ResponseEntity.badRequest().body(errors);
            }

            List<TargetCommissionResponse> targetCommissionResponseList = targetCommissionResponseMapper.fromEntityToDTOList(data);
            return ResponseEntity.ok(targetCommissionResponseList);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error processing file: " + e.getMessage());
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            List<TargetCommission> data = targetCommissionExcelService.parseExcelFile(file);
            List<String> errors = targetCommissionExcelService.validateData(data);

            if (!errors.isEmpty()) {
                return ResponseEntity.badRequest().body(errors);
            }

            targetCommissionService.saveAll(data);
            return ResponseEntity.ok("File uploaded and data saved successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error processing file: " + e.getMessage());
        }
    }

    private boolean hasValidExtension(String filename) {
        String extension = getExtension(filename);
        return VALID_EXTENSIONS.contains(extension);
    }

    private String getExtension(String filename) {
        String[] parts = filename.split("\\.");
        return parts.length > 1 ? parts[parts.length - 1].toLowerCase() : "";
    }
}
