package com.cdg.cdg_incentive_backend.module.employee.controller;

import com.cdg.cdg_incentive_backend.module.employee.entity.Employee;
import com.cdg.cdg_incentive_backend.module.employee.service.EmployeeService;
import com.cdg.cdg_incentive_backend.module.employee.service.impl.EmployeeExcelService;
import com.cdg.cdg_incentive_backend.shared.dto.response.FilterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeExcelService employeeExcelService;

    private static final List<String> VALID_EXTENSIONS = Arrays.asList("xlsx", "xls");

    @GetMapping
    public ResponseEntity<Page<Employee>> getEmployee(
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "employeeId", required = false) String employeeId,
            @RequestParam(value = "branchCode", required = false) String branchCode,
            @RequestParam(value = "branchBU", required = false) String branchBU,
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize
    ) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(employeeService.getAll(
                year,
                month,
                employeeId,
                branchCode,
                branchBU,
                pageRequest));
    }

    @GetMapping("/filter/year")
    public ResponseEntity<List<FilterResponse>> getFilterYear(
    ) {
        return ResponseEntity.ok(employeeService.getDistinctFilter("year"));
    }

    @GetMapping("/filter/month")
    public ResponseEntity<List<FilterResponse>> getFilterMonth(
    ) {
        return ResponseEntity.ok(employeeService.getDistinctFilter("month"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getOneByCriteria(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping("/upload/validate")
    public ResponseEntity<?> validateFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            if (filename == null || !hasValidExtension(filename)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file extension");
            }

            List<Employee> data = employeeExcelService.parseExcelFile(file);
            List<String> errors = employeeExcelService.validateData(data);

            if (!errors.isEmpty()) {
                return ResponseEntity.badRequest().body(errors);
            }

            return ResponseEntity.ok(data);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error processing file: " + e.getMessage());
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            List<Employee> data = employeeExcelService.parseExcelFile(file);
            List<String> errors = employeeExcelService.validateData(data);

            if (!errors.isEmpty()) {
                return ResponseEntity.badRequest().body(errors);
            }

            employeeService.saveAll(data);
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
