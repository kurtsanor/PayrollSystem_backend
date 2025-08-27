package org.example.service;

import java.lang.module.ModuleDescriptor.Builder;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.example.dto.LeaveRequestDTO;
import org.example.model.Employee;
import org.example.model.LeaveRequest;
import org.example.model.LeaveType;
import org.example.repository.LeaveRepository;
import org.example.repository.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveService {
    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    @Autowired
    private EmployeeService employeeService;


    public LeaveRequest createLeave(LeaveRequestDTO dto) throws SQLException {
        Employee employee = employeeService.getEmployeeById(dto.getEmpId());
        LeaveType leaveType = leaveTypeRepository.findByType(dto.getLeaveType());
        LocalDateTime now = LocalDateTime.now();

        LeaveRequest request = LeaveRequest.builder()
            .employee(employee)
            .leaveType(leaveType)
            .startDate(dto.getStartDate())
            .endDate(dto.getEndDate())
            .status("Pending")
            .submittedDate(now)
            .remarks(dto.getRemarks()).build();
        
        return leaveRepository.save(request);
    }
}
