package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetinhouse.entity;

import com.cdg.cdg_incentive_backend.entity.BaseEntity;
import com.cdg.cdg_incentive_backend.module.brand.entity.Brand;
import com.cdg.cdg_incentive_backend.module.targetbranch.entity.TargetBranch;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "target_inhouse")
@EqualsAndHashCode(callSuper = true)
public class TargetInHouse extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "group_brand")
    private String groupBrand;
    @Column(name = "goal_brand")
    private BigDecimal goalBrand;
    @ManyToOne
    @JoinColumn(name = "target_branch_id")
    private TargetBranch targetBranch;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}