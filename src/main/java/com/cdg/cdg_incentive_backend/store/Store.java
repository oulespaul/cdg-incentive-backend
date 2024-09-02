package com.cdg.cdg_incentive_backend.store;

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
@Table(name = "store")
@EqualsAndHashCode(callSuper = true)
public class Store extends BaseEntity {
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
    @Column(name = "store_no")
    private String storeNumber;
    @Column(name = "store_code")
    private String storeCode;
    @OneToMany(mappedBy = "store")
    private Set<AppUser> appUsers = new HashSet<>();
    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private Set<TargetCommission> targetCommissions = new HashSet<>();
}

