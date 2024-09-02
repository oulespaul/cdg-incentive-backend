package com.cdg.cdg_incentive_backend.branch;

import com.cdg.cdg_incentive_backend.entity.AppUser;
import com.cdg.cdg_incentive_backend.entity.BaseEntity;
import com.cdg.cdg_incentive_backend.targetcommission.entity.TargetCommission;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "branch")
@EqualsAndHashCode(callSuper = true)
public class Branch extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "branch_no")
    private String branchNo;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @OneToMany(mappedBy = "branch")
    private Set<AppUser> appUsers = new HashSet<>();

    @OneToMany(mappedBy = "branch")
    @JsonIgnore
    private Set<TargetCommission> targetCommissions = new HashSet<>();
}

