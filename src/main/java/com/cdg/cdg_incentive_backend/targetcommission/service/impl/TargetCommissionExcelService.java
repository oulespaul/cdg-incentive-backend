package com.cdg.cdg_incentive_backend.targetcommission.service.impl;

import com.cdg.cdg_incentive_backend.service.AbstractExcelService;
import com.cdg.cdg_incentive_backend.store.Store;
import com.cdg.cdg_incentive_backend.store.service.StoreService;
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
    private final StoreService storeService;

    @Override
    protected TargetCommission parseRow(Row row) {
        List<Store> storeList = storeService.getAll();
        Store store = getStoreByStoreNumber(storeList, getCellStringValue(row.getCell(2)));

        TargetCommission dto = new TargetCommission();
        dto.setYear(getCellStringValue(row.getCell(0)));
        dto.setMonth(getCellStringValue(row.getCell(1)));
        dto.setStore(store);
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

    private Store getStoreByStoreNumber(List<Store> storeList, String storeNumber) {
        return storeList.stream()
                .filter(store -> store.getStoreNumber().equals(storeNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Store not found"));
    }
}