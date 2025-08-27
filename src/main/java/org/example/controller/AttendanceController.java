package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.model.Attendance;
import org.example.model.Employee;
import org.example.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.YearMonth;
import java.util.List;

@Controller
public class AttendanceController {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @GetMapping("/attendance")
    public String attendance(HttpSession session, Model model) {
        Employee employee = (Employee)session.getAttribute("employee");
        List<Attendance> attendances = attendanceRepository.findByEmployeeId(employee.getId());
        double hoursWorked = attendances.stream().mapToDouble(Attendance::getDuration).sum();
        model.addAttribute("attendances",attendances);
        model.addAttribute("hoursWorked",hoursWorked);
        return "attendance";
    }

    @GetMapping("/attendance/filter")
    public String filterAttendance(@RequestParam String monthPicker, HttpSession session, Model model) {
        YearMonth yearMonth = YearMonth.parse(monthPicker);
        Employee employee = (Employee) session.getAttribute("employee");
        List<Attendance> attendances = attendanceRepository.findByEmployeeIdAndMonthAndYear(employee.getId(), yearMonth.getMonthValue(), yearMonth.getYear());
        double hoursWorked = attendances.stream().mapToDouble(Attendance::getDuration).sum();
        model.addAttribute("attendances",attendances);
        model.addAttribute("hoursWorked",hoursWorked);
        return "attendance :: result";
    }

}
