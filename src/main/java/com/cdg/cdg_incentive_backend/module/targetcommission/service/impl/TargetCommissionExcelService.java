package com.cdg.cdg_incentive_backend.module.targetcommission.service.impl;

import com.cdg.cdg_incentive_backend.module.branch.entity.Branch;
import com.cdg.cdg_incentive_backend.module.branch.service.BranchService;
import com.cdg.cdg_incentive_backend.module.targetcommission.entity.TargetCommission;
import com.cdg.cdg_incentive_backend.shared.service.AbstractExcelService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    private Branch getBranchByBranchNumber(List<Branch> branchList, String branchNumber) {
        return branchList.stream()
                .filter(branch -> branch.getBranchNumber().equals(branchNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Branch not found"));
    }
}
