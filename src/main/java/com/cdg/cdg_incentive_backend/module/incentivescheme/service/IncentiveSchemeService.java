package com.cdg.cdg_incentive_backend.module.incentivescheme.service;

import com.cdg.cdg_incentive_backend.module.incentivescheme.dto.request.CreateIncentiveSchemeRequest;
import com.cdg.cdg_incentive_backend.module.incentivescheme.dto.response.IncentiveSchemeResponse;
import com.cdg.cdg_incentive_backend.module.incentivescheme.entity.IncentiveScheme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IncentiveSchemeService {
    Page<IncentiveSchemeResponse> getAllByCriteria(String schemeKey, String schemeName, Pageable pageable);

    IncentiveScheme createIncentiveScheme(CreateIncentiveSchemeRequest createIncentiveSchemeRequest);
}
