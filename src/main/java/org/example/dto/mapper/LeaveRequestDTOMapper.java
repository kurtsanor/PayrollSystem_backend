package org.example.dto.mapper;

import org.example.dto.LeaveRequestDTO;
import org.example.model.LeaveRequest;

public class LeaveRequestDTOMapper {
    public static LeaveRequestDTO toDTO(LeaveRequest request) {
        return LeaveRequestDTO.builder()
                .id(request.getId())
                .empId(request.getEmployee().getId())
                .leaveType(request.getLeaveType().getType())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .status(request.getStatus())
                .submittedDate(request.getSubmittedDate())
                .processedDate(request.getProcessedDate())
                .remarks(request.getRemarks()).build();
    }
}
