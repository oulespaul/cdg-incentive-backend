package com.cdg.cdg_incentive_backend.module.incentivescheme.repository;

import com.cdg.cdg_incentive_backend.module.incentivescheme.entity.IncentiveScheme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IncentiveSchemeRepository extends JpaRepository<IncentiveScheme, Integer> {
    @Query("""
            SELECT is
            FROM IncentiveScheme is
            WHERE (:schemeKey IS NULL OR is.schemeKey LIKE %:schemeKey%)
            AND (:schemeName IS NULL OR is.schemeName LIKE %:schemeName%)
            """)
    Page<IncentiveScheme> findAllByCriteria(String schemeKey, String schemeName, Pageable pageable);
}
