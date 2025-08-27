package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendanceID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "employeeID")
    private Employee employee;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "timeIn")
    private LocalTime timeIn;

    @Column(name = "timeOut")
    private LocalTime timeOut;

    public double getDuration() {
        return Duration.between(timeIn, timeOut).toMinutes() / 60.0;
    }
}