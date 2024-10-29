package com.cdg.cdg_incentive_backend.module.incentivescheme.entity;

import com.cdg.cdg_incentive_backend.module.department.entity.Department;
import com.cdg.cdg_incentive_backend.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "incentive_scheme")
public class IncentiveScheme extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "scheme_key")
    private String schemeKey;
    @Column(name = "scheme_name")
    private String schemeName;
    @Column(name = "target_using")
    private String targetUsing;
    @Column(name = "is_can_calculate_shrinkgate")
    private Boolean isCanCalculateShrinkgate;
    @Column(name = "is_require_brand_data")
    private Boolean isRequireBrandData;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "incentive_scheme_segment",
            joinColumns = @JoinColumn(name = "incentive_scheme_id"),
            inverseJoinColumns = @JoinColumn(name = "incentive_segment_id"))
    private Set<IncentiveSegment> incentiveSegments = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "incentive_scheme_department",
            joinColumns = @JoinColumn(name = "incentive_scheme_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id"))
    private Set<Department> departments = new HashSet<>();
    @OneToMany(mappedBy = "incentiveScheme", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<IncentiveSchemeMajorGroupCalculation> incentiveSchemeMajorGroupCalculations = new HashSet<>();

    public void addIncentiveSchemeMajorGroupCalculation(IncentiveSchemeMajorGroupCalculation incentiveSchemeMajorGroupCalculation) {
        this.getIncentiveSchemeMajorGroupCalculations().add(incentiveSchemeMajorGroupCalculation);
    }
}
