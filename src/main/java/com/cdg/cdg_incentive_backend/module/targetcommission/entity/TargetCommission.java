package com.cdg.cdg_incentive_backend.module.targetcommission.entity;

import com.cdg.cdg_incentive_backend.entity.BaseEntity;
import com.cdg.cdg_incentive_backend.module.branch.entity.Branch;
import com.cdg.cdg_incentive_backend.module.targetbranch.entity.TargetBranch;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "target_commission")
public class TargetCommission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "year")
    private String year;
    @Column(name = "month")
    private String month;
    @Column(name = "com_tg_total")
    private BigDecimal comTgTotal;
    @Column(name = "actual_ly_total")
    private BigDecimal actualLyTotal;
    @Column(name = "acutal_ly_id")
    private BigDecimal actualLyId;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    @OneToOne(mappedBy = "targetCommission", cascade = CascadeType.ALL)
    private TargetBranch targetBranch;
}

