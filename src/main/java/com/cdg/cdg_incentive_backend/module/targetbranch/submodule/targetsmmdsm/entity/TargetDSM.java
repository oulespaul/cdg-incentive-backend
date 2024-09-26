package com.cdg.cdg_incentive_backend.module.targetbranch.submodule.targetsmmdsm.entity;

import com.cdg.cdg_incentive_backend.entity.BaseEntity;
import com.cdg.cdg_incentive_backend.module.department.entity.Department;
import com.cdg.cdg_incentive_backend.module.subdepartment.entity.SubDepartment;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "target_dsm")
public class TargetDSM extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "dsm_id")
    private String dsmId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_smm_id")
    private TargetSMM targetSMM;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Department department;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_dept_id")
    private SubDepartment subDepartment;
    @Column(name = "goal_dept")
    private BigDecimal goalDept;
    @Column(name = "actual_sale_ly")
    private BigDecimal actualSalesLastYear;
    @Column(name = "goal_id")
    private BigDecimal goalId;
    @Column(name = "actual_sale_id_ly")
    private BigDecimal actualSalesIDLastYear;
}

