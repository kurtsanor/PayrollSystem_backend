package org.example.dto.mapper;

import org.example.dto.AttendanceDTO;
import org.example.model.Attendance;

public class AttendanceDTOMapper {
    public static AttendanceDTO toDTO (Attendance attendance) {
        return AttendanceDTO.builder()
            .id(attendance.getId())
            .empId(attendance.getEmployee().getId())
            .date(attendance.getDate())
            .timeIn(attendance.getTimeIn())
            .timeOut(attendance.getTimeOut()).build();
    }
}
