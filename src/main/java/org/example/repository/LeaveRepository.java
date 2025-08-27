package org.example.repository;

import java.util.List;

import org.example.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<LeaveRequest, Integer> {
    List<LeaveRequest> findByEmployeeId(int id);

    List<LeaveRequest> findByStatus(String status);
}
