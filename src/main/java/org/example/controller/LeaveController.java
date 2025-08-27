package org.example.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.example.dto.LeaveRequestDTO;
import org.example.dto.mapper.LeaveRequestDTOMapper;
import org.example.model.LeaveRequest;
import org.example.repository.LeaveRepository;
import org.example.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/leaves")
public class LeaveController {
    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private LeaveService leaveService;

    @GetMapping("/")
    public ResponseEntity<?> getAllLeaves() {
        List<LeaveRequestDTO> leaves = leaveRepository.findAll()
                .stream().map(LeaveRequestDTOMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<?> getLeaveByEmpId(@PathVariable int empId) {
        List<LeaveRequestDTO> leaves = leaveRepository.findByEmployeeId(empId)
                .stream().map(LeaveRequestDTOMapper::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/status")
    public ResponseEntity<?> getLeaveByStatus(@RequestParam(required = false) String filter) {
        List<LeaveRequestDTO> leaves = leaveRepository.findByStatus(filter)
                .stream().map(LeaveRequestDTOMapper::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok(leaves);
    }

    @PostMapping("/")
    public ResponseEntity<?> createLeaveRequest(@RequestBody LeaveRequestDTO dto) throws SQLException{
        LeaveRequest leaveRequest = leaveService.createLeave(dto);
        System.out.println("Over here");
        // System.out.println("Is request null = " + leaveRequest == null);
        return leaveRequest != null ? 
        ResponseEntity.ok(LeaveRequestDTOMapper.toDTO(leaveRequest)) 
        : ResponseEntity.badRequest().build();
    }

}
