package com.cdg.cdg_incentive_backend.targetcommission.service;

import com.cdg.cdg_incentive_backend.branch.Branch;
import com.cdg.cdg_incentive_backend.branch.service.BranchService;
import com.cdg.cdg_incentive_backend.service.AbstractExcelService;
import com.cdg.cdg_incentive_backend.targetcommission.entity.TargetCommission;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TargetCommissionExcelService extends AbstractExcelService<TargetCommission> {
    private final BranchService branchService;

    @Override
    protected TargetCommission parseRow(Row row) {
        List<Branch> branchList = branchService.getAll();
        Branch branch = getBranchByBranchNumber(branchList, getCellStringValue(row.getCell(2)));

        TargetCommission dto = new TargetCommission();
        dto.setYear(getCellStringValue(row.getCell(0)));
        dto.setMonth(getCellStringValue(row.getCell(1)));
        dto.setBranch(branch);
        dto.setComTgTotal(getCellBigDecimalValue(row.getCell(5)));
        dto.setActualLyTotal(getCellBigDecimalValue(row.getCell(6)));
        dto.setActualLyId(getCellBigDecimalValue(row.getCell(7)));
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy("SYSTEM");
        return dto;
    }

    @Override
    public List<String> validateData(List<TargetCommission> data) {
        List<String> errors = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            TargetCommission row = data.get(i);

            if (row.getYear() == null || row.getYear().isEmpty()) {
                errors.add("Row " + (i + 1) + ": 'Year' is missing.");
            }
            if (row.getMonth() == null || row.getMonth().isEmpty()) {
                errors.add("Row " + (i + 1) + ": 'Month' is missing.");
            }
            if (row.getComTgTotal() == null) {
                errors.add("Row " + (i + 1) + ": 'Com Tg Total' is missing.");
            }
        }
        return errors;
    }

    private Branch getBranchByBranchNumber(List<Branch> branchList, String branchNumber) {
        return branchList.stream()
                .filter(branch -> branch.getBranchNo().equals(branchNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Branch not found"));
    }
}
