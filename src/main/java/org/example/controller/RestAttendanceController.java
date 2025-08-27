package org.example.controller;

import java.sql.SQLException;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import org.example.dto.mapper.AttendanceDTOMapper;
import org.example.model.Attendance;
import org.example.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@CrossOrigin(origins = "https://motorphpayroll.netlify.app/")
@RequestMapping("/attendance")
public class RestAttendanceController {
    
    @Autowired
    private AttendanceRepository attendanceRepository;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getAttendanceByEmpId(@PathVariable int id) throws SQLException {
        List<Attendance> attendances = attendanceRepository.findByEmployeeId(id);
        
        return ResponseEntity.ok(attendances.stream()
            .map(AttendanceDTOMapper:: toDTO)
            .collect(Collectors.toList()));
    }

    @GetMapping("/{id}/{period}")
    public ResponseEntity<?> getAttendanceByEmpIdAndPeriod(@PathVariable int id, @PathVariable String period) {
        try {
            YearMonth yearMonth = YearMonth.parse(period);
            int month = yearMonth.getMonthValue();
            int year = yearMonth.getYear();
            
            List<Attendance> attendances = attendanceRepository.findByEmployeeIdAndMonthAndYear(id, month, year);

            return ResponseEntity.ok(attendances.stream()
                .map(AttendanceDTOMapper:: toDTO)
                .collect(Collectors.toList()));
            
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid period format. Use YYYY-MM.");
        }
    }
    


    
}
