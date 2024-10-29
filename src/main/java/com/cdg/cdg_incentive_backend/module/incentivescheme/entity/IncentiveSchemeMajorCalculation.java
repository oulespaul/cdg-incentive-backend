package com.cdg.cdg_incentive_backend.module.incentivescheme.entity;

import com.cdg.cdg_incentive_backend.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "incentive_scheme_major_calculation")
public class IncentiveSchemeMajorCalculation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "min_incentive")
    private BigDecimal minIncentive;
    @Column(name = "max_incentive")
    private BigDecimal maxIncentive;
    @Column(name = "incentive")
    private BigDecimal incentive;
    @Column(name = "unit")
    private String unit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incentive_scheme_major_group_calculation_id")
    private IncentiveSchemeMajorGroupCalculation incentiveSchemeMajorGroupCalculation;
}
