package org.example.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "leaves")
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leaveID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "employeeID")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "leaveTypeID")
    private LeaveType leaveType;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;

    @Column(name = "status")
    private String status;

    @Column(name = "submittedDate")
    private LocalDateTime submittedDate;

    @Column(name = "processedDate")
    private LocalDateTime processedDate;

    @Column(name = "remarks")
    private String remarks;

}
