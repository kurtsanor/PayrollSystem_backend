package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "salary")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salaryID")
    private int id;

    @OneToOne
    @JoinColumn(name = "employeeID") // references the empid column in the salary table
    private Employee employee;

    @Column(name = "basicSalary")
    private double basicSalary;

    @Column(name = "grossSemiMonthlyRate")
    private double grossSemiMonthly;

    @Column(name = "hourlyRate")
    private double hourlyRate;

}

