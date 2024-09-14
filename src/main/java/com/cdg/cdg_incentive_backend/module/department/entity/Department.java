package com.cdg.cdg_incentive_backend.module.department.entity;

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
@Table(name = "department")
@EqualsAndHashCode(callSuper = true)
public class Department extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "department_name")
    private String department_name;
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<Brand> brands = new HashSet<>();
}