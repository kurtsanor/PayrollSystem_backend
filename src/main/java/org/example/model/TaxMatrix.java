package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "taxmatrix")
public class TaxMatrix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taxMatrixID")
    private int id;

    @Column(name = "minSalary")
    private double minSalary;

    @Column(name = "maxSalary")
    private double maxSalary;

    @Column(name = "baseTax")
    private double baseTax;

    @Column(name = "excessRate")
    private double excessRate;

    @Column(name = "excessBase")
    private double excessBase;
}
