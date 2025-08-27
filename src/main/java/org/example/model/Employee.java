package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "positionID")
    private Position position;


    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Salary salary;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private GovernmentId governmentId;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Allowance> allowances = new ArrayList<>();

}
