package org.example.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AttendanceDTO {
    private Integer id;
    private Integer empId;
    private LocalDate date;
    private LocalTime timeIn;
    private LocalTime timeOut;
}
