package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.entity;

import com.cdg.cdg_incentive_backend.shared.entity.BaseEntity;
import com.cdg.cdg_incentive_backend.module.brand.entity.Brand;
import com.cdg.cdg_incentive_backend.module.targetbranch.entity.TargetBranch;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "target_inhouse")
public class TargetInHouse extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "group_brand")
    private String groupBrand;
    @Column(name = "goal_brand")
    private BigDecimal goalBrand;
    @Column(name = "actual_sales_id_last_year")
    private BigDecimal actualSalesIDLastYear;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_branch_id")
    private TargetBranch targetBranch;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;
}