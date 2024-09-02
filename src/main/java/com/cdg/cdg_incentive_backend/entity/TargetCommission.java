package com.cdg.cdg_incentive_backend.entity;

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
@EqualsAndHashCode(callSuper = true)
public class TargetCommission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String year;

    private String month;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Column(name = "com_tg_total")
    private BigDecimal comTgTotal;

    @Column(name = "actual_ly_total")
    private BigDecimal actualLyTotal;

    @Column(name = "acutal_ly_id")
    private BigDecimal actualLyId;
}

