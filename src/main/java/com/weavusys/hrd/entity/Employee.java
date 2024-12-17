package com.weavusys.hrd.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    private String id;

    private String name;
    private LocalDate entryDate;
    private LocalDate exitDate;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    private LocalDate conversionDate;

    private Integer year;

    private Integer rank;

    private Integer status;
    public enum EmployeeType {
        REGULAR, CONTRACT
    }
}
