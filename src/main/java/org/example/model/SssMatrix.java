package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sssmatrix")
public class SssMatrix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sssMatrixID")
    private int id;

    @Column(name = "minSalary")
    private double minSalary;

    @Column(name = "maxSalary")
    private double maxSalary;

    @Column(name = "contribution")
    private double contribution;

}
