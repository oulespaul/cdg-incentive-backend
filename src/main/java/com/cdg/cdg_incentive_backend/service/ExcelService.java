package com.cdg.cdg_incentive_backend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExcelService<T> {
    List<T> parseExcelFile(MultipartFile file) throws IOException;

    List<String> validateData(List<T> data);
}
