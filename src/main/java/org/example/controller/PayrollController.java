package org.example.controller;

import jakarta.servlet.http.HttpSession;

import org.example.dto.mapper.PayslipDTOMapper;
import org.example.model.Employee;
import org.example.model.Payslip;
import org.example.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.time.YearMonth;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @GetMapping("/{id}/{monthPicker}")
    public ResponseEntity<?> generatePayslip(@PathVariable String monthPicker, @PathVariable int id) throws SQLException {
        YearMonth yearMonth = YearMonth.parse(monthPicker);
        Payslip payslip = payrollService.generatePayslip(id, yearMonth.getMonthValue(), yearMonth.getYear());
        return payslip  != null ? ResponseEntity.ok(PayslipDTOMapper.toDto(payslip)) : ResponseEntity.badRequest().build();
    }
}
