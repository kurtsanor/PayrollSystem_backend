// package org.example;

// import static org.junit.jupiter.api.Assertions.assertNotNull;

// import java.time.LocalDate;
// import java.time.LocalDateTime;

// import org.example.model.Employee;
// import org.example.model.LeaveRequest;
// import org.example.model.LeaveType;
// import org.example.repository.EmployeeRepository;
// import org.example.repository.LeaveRepository;
// import org.example.repository.LeaveTypeRepository;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// public class LeaveTest {
//     @Autowired
//     private LeaveRepository leaveRepository;

//     @Autowired
//     private LeaveTypeRepository leaveTypeRepository;

//     @Autowired
//     private EmployeeRepository employeeRepository;

//     @Test
//     public void createLeaveTest() {
//         Employee employee = employeeRepository.findById(1).orElseThrow();
//         LeaveType leaveType = leaveTypeRepository.findByType("Vacation");
//         LocalDateTime now = LocalDateTime.now();

//         LeaveRequest request = LeaveRequest.builder()
//             .employee(employee)
//             .leaveType(leaveType)
//             .startDate(LocalDate.of(2025, 1, 5))
//             .endDate(LocalDate.of(2025, 1, 10))
//             .status("Pending")
//             .submittedDate(now)
//             .remarks("test remarks").build();

//         LeaveRequest requestAfterSubmit = leaveRepository.save(request);
//         assertNotNull(requestAfterSubmit);
//     }
// }
