package com.cdg.cdg_incentive_backend.module.incentivescheme.repository;

import com.cdg.cdg_incentive_backend.module.incentivescheme.entity.IncentiveSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncentiveSegmentRepository extends JpaRepository<IncentiveSegment, Integer> {
    @Query("""
            SELECT is
            FROM IncentiveSegment is
            WHERE is.isActive = true
            """)
    List<IncentiveSegment> findAllActive();
}
