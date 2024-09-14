package com.cdg.cdg_incentive_backend.module.subdepartment.entity;

import com.cdg.cdg_incentive_backend.entity.BaseEntity;
import com.cdg.cdg_incentive_backend.module.brand.entity.Brand;
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
@Table(name = "sub_department")
@EqualsAndHashCode(callSuper = true)
public class SubDepartment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sub_department_name")
    private String sub_department_name;
    @OneToMany(mappedBy = "subDepartment")
    @JsonIgnore
    private Set<Brand> brands = new HashSet<>();
}