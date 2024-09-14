package com.cdg.cdg_incentive_backend.branch;

import com.cdg.cdg_incentive_backend.entity.AppUser;
import com.cdg.cdg_incentive_backend.entity.BaseEntity;
import com.cdg.cdg_incentive_backend.targetcommission.entity.TargetCommission;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "bu")
    private String bu;
    @Column(name = "brand")
    private String brand;
    @Column(name = "segment")
    private String segment;
    @Column(name = "name")
    private String name;
    @Column(name = "region_name")
    private String regionName;
    @Column(name = "type")
    private String type;
    @Column(name = "branch_no")
    private String branchNumber;
    @Column(name = "branch_code")
    private String branchCode;
    @OneToMany(mappedBy = "branch")
    private Set<AppUser> appUsers = new HashSet<>();
    @OneToMany(mappedBy = "branch")
    @JsonIgnore
    private Set<TargetCommission> targetCommissions = new HashSet<>();
}

