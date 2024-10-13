package com.cdg.cdg_incentive_backend.module.employee.entity;

import com.cdg.cdg_incentive_backend.shared.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "'Business Unit' is missing.")
    @Column(name = "business_unit")
    private String businessUnit;

    @NotEmpty(message = "'Company' is missing.")
    @Column(name = "company")
    private String company;

    @NotEmpty(message = "'Branch' is missing.")
    @Column(name = "branch")
    private String branch;

    @NotEmpty(message = "'Branch Description' is missing.")
    @Column(name = "branch_description")
    private String branchDescription;

    @NotEmpty(message = "'Employee ID' is missing.")
    @Column(name = "employee_id")
    private String employeeId;

    @NotNull(message = "'Hire Date' is missing.")
    @Column(name = "hire_date")
    private LocalDate hireDate;

    @NotEmpty(message = "'Employee Group' is missing.")
    @Column(name = "employee_group")
    private String employeeGroup;

    @NotEmpty(message = "'Position Description' is missing.")
    @Column(name = "position_description")
    private String positionDescription;

    @NotEmpty(message = "'Cost Center' is missing.")
    @Column(name = "cost_center")
    private String costCenter;

    @NotEmpty(message = "'Corporate Title' is missing.")
    @Column(name = "corporate_title")
    private String corporateTitle;

    @Column(name = "terminated_date")
    private LocalDate terminatedDate;

    @NotEmpty(message = "'Scheme' is missing.")
    @Column(name = "scheme")
    private String scheme;

    @NotEmpty(message = "'New Cost Center' is missing.")
    @Column(name = "new_cost_center")
    private String newCostCenter;

    @Column(name = "brand_id")
    private String brandId;

    @NotEmpty(message = "'Branch No' is missing.")
    @Column(name = "branch_no")
    private String branchNo;

    @NotNull(message = "'Day Working' is missing.")
    @Column(name = "day_working")
    private Integer dayWorking;
}
