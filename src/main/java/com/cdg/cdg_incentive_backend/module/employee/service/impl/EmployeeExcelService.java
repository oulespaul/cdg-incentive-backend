package com.cdg.cdg_incentive_backend.module.employee.service.impl;

import com.cdg.cdg_incentive_backend.module.employee.entity.Employee;
import com.cdg.cdg_incentive_backend.shared.service.AbstractExcelService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RequiredArgsConstructor
@Service
public class EmployeeExcelService extends AbstractExcelService<Employee> {
    @Override
    protected Employee parseRow(Row row) {
        Employee dto = new Employee();

        dto.setBusinessUnit(getCellStringValue(row.getCell(0)));
        dto.setCompany(getCellStringValue(row.getCell(1)));
        dto.setBranch(getCellStringValue(row.getCell(2)));
        dto.setBranchDescription(getCellStringValue(row.getCell(3)));
        dto.setEmployeeId(getCellStringValue(row.getCell(4)));
        dto.setHireDate(row.getCell(5).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        dto.setEmployeeGroup(getCellStringValue(row.getCell(6)));
        dto.setPositionDescription(getCellStringValue(row.getCell(7)));
        dto.setCostCenter(getCellStringValue(row.getCell(8)));
        dto.setCorporateTitle(getCellStringValue(row.getCell(9)));
        if (row.getCell(10) != null && row.getCell(10).getCellType() != CellType.BLANK) {
            dto.setHireDate(row.getCell(10).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        dto.setScheme(getCellStringValue(row.getCell(11)));
        dto.setNewCostCenter(getCellStringValue(row.getCell(12)));
        dto.setBrandId(getCellStringValue(row.getCell(13)));
        dto.setBranchNo(getCellStringValue(row.getCell(14)));
        dto.setDayWorking(getCellIntegerValue(row.getCell(15)));
        dto.setCreatedAt(LocalDateTime.now());
        dto.setCreatedBy("SYSTEM");
        return dto;
    }
}