package com.cdg.cdg_incentive_backend.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class AbstractExcelService<T> implements ExcelService<T> {

    @Override
    public List<T> parseExcelFile(MultipartFile file) throws IOException {
        List<T> data = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                T dto = parseRow(row);
                data.add(dto);
            }
        }
        return data;
    }

    protected abstract T parseRow(Row row);

    public List<String> validateData(List<T> employees) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        List<String> errors = new ArrayList<>();

        for (int i = 0; i < employees.size(); i++) {
            T employee = employees.get(i);
            Set<ConstraintViolation<T>> violations = validator.validate(employee);

            for (ConstraintViolation<T> violation : violations) {
                errors.add("Row " + (i + 1) + ": " + violation.getMessage());
            }
        }

        return errors;
    }

    protected String getCellStringValue(Cell cell) {
        if (cell == null) return null;
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> {
                double numericValue = cell.getNumericCellValue();
                if (numericValue == (long) numericValue) {
                    yield String.valueOf((long) numericValue);
                } else {
                    yield String.valueOf(numericValue);
                }
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> null;
        };
    }


    protected Integer getCellIntegerValue(Cell cell) {
        if (cell == null) return null;
        switch (cell.getCellType()) {
            case NUMERIC:
                return (int) cell.getNumericCellValue();
            case STRING:
                try {
                    return Integer.valueOf(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return null;
                }
            default:
                return null;
        }
    }

    protected BigDecimal getCellBigDecimalValue(Cell cell) {
        if (cell == null) return null;
        switch (cell.getCellType()) {
            case NUMERIC:
                return BigDecimal.valueOf(cell.getNumericCellValue());
            case STRING:
                try {
                    return new BigDecimal(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return null;
                }
            default:
                return null;
        }
    }
}