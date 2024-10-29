package com.cdg.cdg_incentive_backend.module.incentivescheme.entity;

import com.cdg.cdg_incentive_backend.shared.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "incentive_scheme_major_group_calculation")
public class IncentiveSchemeMajorGroupCalculation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "min_target_per_head")
    private BigDecimal minTargetPerHead;
    @Column(name = "max_target_per_head")
    private BigDecimal maxTargetPerHead;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "incentive_scheme_id")
    private IncentiveScheme incentiveScheme;
    @OneToMany(mappedBy = "incentiveSchemeMajorGroupCalculation", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<IncentiveSchemeMajorCalculation> incentiveSchemeMajorCalculations = new HashSet<>();

    public void addIncentiveSchemeMajorCalculation(IncentiveSchemeMajorCalculation incentiveSchemeMajorCalculation) {
        this.getIncentiveSchemeMajorCalculations().add(incentiveSchemeMajorCalculation);
    }
}
