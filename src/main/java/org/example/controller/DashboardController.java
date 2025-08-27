package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.model.Employee;
import org.example.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.sql.SQLException;

@Controller
public class DashboardController {

    private final EmployeeService service;

    public DashboardController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) throws SQLException {
        Integer employeeId = (Integer) session.getAttribute("employeeId");
        if (employeeId == null || employeeId == -1) return "redirect:/";

        Employee employee = service.getEmployeeWithAllowanceById(employeeId);
        model.addAttribute("employee", employee);
        session.setAttribute("employee", employee);

        return "dashboard";
    }

    @PostMapping("/dashboard")
    public String gotoDashboard() {
        return "redirect:/dashboard";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/payslip")
    public String showPayslip(HttpSession session, Model model) {
        Employee employee = (Employee)session.getAttribute("employee");
        model.addAttribute("employee",employee);
        return "payslip";
    }

    @PostMapping("/payslip")
    public String gotoPayslip() {
        return "redirect:/payslip";
    }

}
