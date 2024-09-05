package com.cdg.cdg_incentive_backend.targetcommission.controller;

import com.cdg.cdg_incentive_backend.targetcommission.dto.response.TargetCommissionFilterResponse;
import com.cdg.cdg_incentive_backend.targetcommission.dto.response.TargetCommissionResponse;
import com.cdg.cdg_incentive_backend.targetcommission.entity.TargetCommission;
import com.cdg.cdg_incentive_backend.targetcommission.service.TargetCommissionService;
import com.cdg.cdg_incentive_backend.targetcommission.service.impl.TargetCommissionExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/target-commission")
public class TargetCommissionController {
    private final TargetCommissionService targetCommissionService;
    private final TargetCommissionExcelService targetCommissionExcelService;

    @GetMapping
    public ResponseEntity<Page<TargetCommissionResponse>> getTargetCommission(
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "storeNumber", required = false) String storeNumber,
            @RequestParam(value = "storeBU", required = false) String storeBU,
            @RequestParam(value = "storeCode", required = false) String storeCode,
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize
    ) {
        return ResponseEntity.ok(targetCommissionService.getAllResponse(
                year,
                month,
                storeNumber,
                storeBU,
                storeCode,
                page,
                pageSize));
    }

    @GetMapping("/filter/year")
    public ResponseEntity<List<TargetCommissionFilterResponse>> getFilterYearTargetCommission() {
        return ResponseEntity.ok(targetCommissionService.getDistinctYear());
    }

    @GetMapping("/filter/month")
    public ResponseEntity<List<TargetCommissionFilterResponse>> getFilterMonthTargetCommission() {
        return ResponseEntity.ok(targetCommissionService.getDistinctMonth());
    }

    @GetMapping("/filter/store")
    public ResponseEntity<List<TargetCommissionFilterResponse>> getFilterStoreNumberTargetCommission() {
        return ResponseEntity.ok(targetCommissionService.getDistinctStoreNumber());
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
}
