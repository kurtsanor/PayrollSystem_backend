package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "governmentnumber")
public class GovernmentId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "governmentNumberID")
    private int id;

    @OneToOne
    @JoinColumn(name = "employeeID")
    private Employee employee;

    @Column(name = "sssNumber")
    private String sss;

    @Column(name = "philhealthNumber")
    private String philhealth;

    @Column(name = "pagibigNumber")
    private String pagibig;

    @Column(name = "tinNumber")
    private String tin;

}
