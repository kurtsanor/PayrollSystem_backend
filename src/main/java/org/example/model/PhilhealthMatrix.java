package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "philhealthmatrix")
public class PhilhealthMatrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "philhealthMatrixID")
    private int id;

    @Column(name = "minSalary")
    private double minSalary;

    @Column(name = "maxSalary")
    private double maxSalary;

    @Column(name = "premiumRate")
    private double premiumRate;

    @Column(name = "maxContribution")
    private double maxContribution;

    @Column(name = "employeeShareRate")
    private double employeeShareRate;

}
