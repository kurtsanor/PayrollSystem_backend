package org.example.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LeaveRequestDTO {
    private Integer id;
    private Integer empId;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private LocalDateTime submittedDate;
    private LocalDateTime processedDate;
    private String remarks;
}
