package com.cdg.cdg_incentive_backend.module.targetcommission.entity;

import com.cdg.cdg_incentive_backend.shared.entity.BaseEntity;
import com.cdg.cdg_incentive_backend.module.branch.entity.Branch;
import com.cdg.cdg_incentive_backend.module.targetbranch.entity.TargetBranch;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "'Year' is missing.")
    @Column(name = "year")
    private String year;
    @NotEmpty(message = "'Month' is missing.")
    @Column(name = "month")
    private String month;
    @NotEmpty(message = "'Com TG Total' is missing.")
    @Column(name = "com_tg_total")
    private BigDecimal comTgTotal;
    @NotEmpty(message = "'Actual Last year total' is missing.")
    @Column(name = "actual_ly_total")
    private BigDecimal actualLyTotal;
    @NotEmpty(message = "'Actual Last year id' is missing.")
    @Column(name = "acutal_ly_id")
    private BigDecimal actualLyId;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    @OneToOne(mappedBy = "targetCommission", cascade = CascadeType.ALL)
    private TargetBranch targetBranch;
}

