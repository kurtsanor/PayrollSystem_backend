package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pagibigmatrix")
public class PagibigMatrix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pagibigMatrixID")
    private int id;

    @Column(name = "minSalary")
    private double minSalary;

    @Column(name = "maxSalary")
    private double maxSalary;

    @Column(name = "employeeRate")
    private double employeeRate;

    @Column(name = "maxContribution")
    private double maxContribution;
}