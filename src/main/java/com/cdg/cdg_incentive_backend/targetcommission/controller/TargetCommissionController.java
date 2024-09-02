package com.cdg.cdg_incentive_backend.targetcommission.controller;

import com.cdg.cdg_incentive_backend.targetcommission.dto.response.TargetCommissionResponse;
import com.cdg.cdg_incentive_backend.targetcommission.entity.TargetCommission;
import com.cdg.cdg_incentive_backend.targetcommission.repositories.TargetCommissionRepository;
import com.cdg.cdg_incentive_backend.targetcommission.service.TargetCommissionExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/target-commission")
public class TargetCommissionController {
    private final TargetCommissionRepository targetCommissionRepository;
    private final TargetCommissionExcelService targetCommissionExcelService;

    @GetMapping
    public ResponseEntity<List<TargetCommissionResponse>> getTargetCommission() {
        return ResponseEntity.ok(targetCommissionRepository.findAllResponse());
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            List<TargetCommission> data = targetCommissionExcelService.parseExcelFile(file);
            List<String> errors = targetCommissionExcelService.validateData(data);

            if (!errors.isEmpty()) {
                return ResponseEntity.badRequest().body(errors);
            }

            targetCommissionRepository.saveAll(data);
            return ResponseEntity.ok("File uploaded and data saved successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error processing file: " + e.getMessage());
        }
    }
}
